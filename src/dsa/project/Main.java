/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsa.project;
import java.io.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.tartarus.martin.Stemmer;
public class Main {
    static WebPage WebPages[]=new WebPage[1000];
   public static void main(String[] args) throws SAXException, ParserConfigurationException, IOException {

      try {
         File inputFile = new File("D:\\Search Engine Java\\Xml file\\page1.txt");
         SAXParserFactory factory = SAXParserFactory.newInstance();
         SAXParser saxParser = factory.newSAXParser();
         UserHandler userhandler = new UserHandler();
         saxParser.parse(inputFile, userhandler);
         for (int i=0;i<20;i++)
         {
             //([{:;=</>|,%'.*\"+#$_()\\[\\]-[0-9]!&}])
          String patternStr = "[^a-zA-Z0-9\\s]";
          String replacementStr = " ";
         
          // Compile regular expression
          Pattern pattern = Pattern.compile(patternStr);
          
          // Replace all occurrences of pattern in input
          Matcher matcher = pattern.matcher(WebPages[i].getText());
          WebPages[i].setText(WebPages[i].getText().replaceAll(patternStr," "));
          String[] removeWords = {"a\\s","b\\s","c\\s","d\\s","e\\s","f\\s","g\\s","h\\s",
              "h\\s","i\\s","j\\s","k\\s","l\\s","m\\s","n\\s",
              "o\\s","p\\s","q\\s","r\\s","s\\s","t\\s","u\\s","v\\s","w\\s","x\\s","y\\s","z\\s",
              "under","with","next","around","through","is","the","th","and","as",
              "each","for","to","have","has","of","off","them","in","it","on","at","an","other"};
          for (int j=0;j<removeWords.length;j++)
          WebPages[i].setText(WebPages[i].getText().replaceAll("\\s"+removeWords[j], " "));
         }
         Stemmer stem;
          for (int i=0;i<20;i++){
              //System.out.println("Id:"+WebPages[i].id+"\n"+WebPages[i].title+"\n"+WebPages[i].body);
              stem = new Stemmer();
              stem.add(WebPages[i].getText().toCharArray(), WebPages[i].getText().length());
              stem.stem();
              WebPages[i].setText(stem.toString());
              String bodywords[] = WebPages[i].getText().split("\\s+");
              System.out.println("\nId: "+WebPages[i].getId());
              System.out.println("Title: "+WebPages[i].getTitle() + " ");
              
              for (int j=0;j<bodywords.length;j++)
                  System.out.print(bodywords[j]+" ");
      }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }   
}

