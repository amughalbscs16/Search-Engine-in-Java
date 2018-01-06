/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsa.project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.tartarus.martin.Stemmer;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.text.DecimalFormat;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author AliHassaanM
 */
public class UserHandler extends DefaultHandler {
   static WebPage Page;
   boolean bTitle = false;
   boolean bRevision = false;
   boolean bText = false;
   boolean bId = false;
   boolean check = false;
   String title = "";
   int id;
   int pagecount = 0;
   @Override
   public void startElement(
      String uri, String localName, String qName, Attributes attributes)
      throws SAXException {
      
      if (qName.equalsIgnoreCase("page")) {
      
      } 
      if(qName.equalsIgnoreCase("id") && !check)
      {
          bId = true;
          check = true;
      }
      else if (qName.equalsIgnoreCase("title")) {
         bTitle = true;
      } 
      else if (qName.equalsIgnoreCase("text")) {
      bText = true;
      }
   }

   @Override
   public void endElement(String uri, 
      String localName, String qName) throws SAXException {
      if (qName.equalsIgnoreCase("text")) {
          try {
              makeWebPage();
          } catch (IOException ex) {
              Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
          }
         characters.setLength(0);
         bText=false;
         check = false;
      } 
   }
    private final StringBuilder characters = new StringBuilder(64);
   @Override
    public void characters(char ch[], int start, int length) throws SAXException {
      
      if (bId) {
         id = Integer.parseInt((new String(ch,start,length)));
         bId = false;
      }
      else if (bTitle)
      {
          title = (new String(ch,start,length));
          bTitle=false;
      }
      else if (bText) {
          characters.append(new String(ch,start,length));
      }
   }
    static int countpages = 0;
    public void makeWebPage() throws IOException
    {
        /*If you start from the middle you need to do this from here
		start doing from where left before*/
        //Count for how many pages have been done;
		//if(id>value){
        countpages +=1 ;
        Page = (new WebPage(this.id,this.title.toLowerCase(),this.characters.toString().toLowerCase()));
        this.pagecount++;
        doWork();
        //}
    }
    public static void doWork() throws IOException
    {     
             //([{:;=</>|,%'.*\"+#$_()\\[\\]-[0-9]!&}])
          String patternStr = "[^a-z0-9\\s]";
          String replacementStr = " ";
         
          // Compile regular expression
          Pattern pattern = Pattern.compile(patternStr);
          
          // Replace all occurrences of pattern in input
          Matcher matcher = pattern.matcher(Page.getText());
          Page.setTitle(Page.getTitle().replaceAll(patternStr," "));
          Page.setText(Page.getText().replaceAll(patternStr," "));
          String[] removeWords = {"a\\s","b\\s","c\\s","d\\s","e\\s","f\\s","g\\s","h\\s",
              "h\\s","i\\s","j\\s","k\\s","l\\s","m\\s","n\\s",
              "o\\s","p\\s","q\\s","r\\s","s\\s","t\\s","u\\s","v\\s","w\\s","x\\s","y\\s","z\\s",
              "under","with","next","around","through","is","the","th","and","as",
              "each","for","to","have","has","of","off","them","in","it","on","at","an","other","all","some","none"};
          for (int j=0;j<removeWords.length;j++){
          Page.setText(Page.getText().replaceAll("\\s"+removeWords[j], " "));
          }
             String titleWords[] = Page.getTitle().split(" ");
             String textWords[] = Page.getText().split(" ");
//                Stemmer stem;
//                for (int i=0;i<titleWords.length;i++)
//                {
//                    stem = new Stemmer();
//                    stem.add(titleWords[i].toCharArray(),titleWords[i].length()-1);
//                    stem.stem();
//                    titleWords[i] = stem.toString();
//                }
                
            //Word to Pages And Writing to Files
            Hashtable<String,WordPage> Table = new Hashtable<>();
       
       WordPage check = new WordPage();
       String directory = "D:\\Search Engine Java\\processeddata\\";
       for (int i=0;i<titleWords.length;i++)
       {
           check = Table.get(titleWords[i]);
       if (check == null){
           Table.put(titleWords[i], new WordPage(Page.getId(),0));
           check = Table.get(titleWords[i]);
           check.incrementCount(1, 0);
           check.setPageRank(check.getPageRank()+ (1/((i+10)/10)));
       } else {
          check.setPageRank(check.getPageRank()+ (1/((i+10)/10)));
          check.incrementCount(1,0);    
       }
       check = null;
       }
       for (int i=0;i<textWords.length;i++)
       {
         check = Table.get(textWords[i]);
         if (check == null){
           Table.put(textWords[i], new WordPage(Page.getId(),1));
           check = Table.get(textWords[i]);
           check.incrementCount(1, 1);
           check.setPageRank(check.getPageRank()+ (0.2/(((i+100)/100))));
         }
         else {
           check.incrementCount(1, 1);
           check.setPageRank(check.getPageRank()+ (0.3/(((i+100)/100))));
         }     
       }
        double pagerank;
        WordPage temporary;
        Set<String> keys = Table.keySet();
        //System.out.println(keys.size());
        DecimalFormat format = new DecimalFormat("#.####");
        for(String key: keys){
            temporary = Table.get(key);
            //System.out.println("Count of "+key+" in title is: "+temporary.getCount(0) +" and body is: "+temporary.getCount(1) + " and page rank is: "+temporary.getPageRank());
            File dir = new File(directory+key);
            boolean dirsuccess = dir.mkdirs();
            if(dirsuccess || dir.exists())  //Append The WordPage after It;
            {
                //System.out.println(key+" Directory Created");
                String filename = directory+key+"\\documents.txt";
                File file = new File(filename);
                boolean filesuccess = file.createNewFile();
                if(filesuccess || file.exists())
                {
                    //System.out.println(key+" \\documents.txt " + "File Exists or Created Successful");
                    try(FileWriter f = new FileWriter(filename, true);
                        BufferedWriter b = new BufferedWriter(f); 
                        PrintWriter p = new PrintWriter(b);)
                    {
                     
                        p.println(Page.getId()+","+format.format(temporary.getPageRank())+","+temporary.getCount(0)+","
                                +temporary.getCount(1)+","+Page.getTitle()+":");
                        
                    } catch (IOException i)
                    { 
                        i.printStackTrace();
                    }
                }
                else 
                {
                    //System.out.println(key+" \\documents.txt "+"Error Occured While Creating File");
                }   
            }
            else {
                //System.out.println(key+" Directory Does not exist and cannot be created");
            }
        }
            System.out.println(Page.getId()+" : "+ countpages);
            try (FileWriter f = new FileWriter("D:\\Search Engine Java\\pagerecord.txt", true);
                    BufferedWriter b = new BufferedWriter(f); 
                    PrintWriter p = new PrintWriter(b);) 
                    {
                        p.println(Page.getId()+"\n");
                        
                    } catch (IOException i)
                    { 
                        i.printStackTrace();
                    }
    }
}
