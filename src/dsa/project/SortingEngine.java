/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsa.project;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.Hashtable;
import java.util.Collections;
import java.util.Comparator;
/**
 *
 * @author AliHassaanM
 */
public class SortingEngine {
static int count = 0;
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    //Hashtable<Integer,Word> Pageid, Word; If word in that hash increment the count; Easy access.
    public static void sortingmain(String[] args) throws IOException {
        File folder = new File("D:\\Search Engine Java\\processeddata");
        File[] listOfFiles = folder.listFiles();
        for (int i=0;i<listOfFiles.length;i++){
            count+=1;
            if (count>147652){
                try{
            SortingEngine.sort(new File(listOfFiles[i].getAbsolutePath()+"\\documents.txt"));
                }
                catch(FileNotFoundException fnfe)
                        {
                        fnfe.printStackTrace();
                        }
            try (FileWriter f = new FileWriter("D:\\Search Engine Java\\sortedpages.txt", true);
                    BufferedWriter b = new BufferedWriter(f); 
                    PrintWriter p = new PrintWriter(b);) 
                    {
                        p.println(count);
                        
                    } catch (IOException j)
                    { 
                        j.printStackTrace();
                    }
            }
        }
          //String adddata = a.split(":\n");
        //System.out.println(adddata1[0]);
        //System.out.println(WordPages[0].toString());
        
        }
    
        public static void sort(File filetosort) throws FileNotFoundException,IOException
        {
            
            Scanner filescanner = new Scanner(filetosort);
            String Content="";
            while (filescanner.hasNext())
            {
                Content+=filescanner.next();
            }
            //System.out.println(Content);
            if(Content!=""){
            //System.out.println(Content);
            String[] word_page_data = Content.split(":");
            int length = word_page_data.length;
            ArrayList<WordPage> word_page_list = new ArrayList<>();
            for (int i=0;i<length;i++)
            {
                word_page_list.add(new WordPage(word_page_data[i]));
                WordPage ali = word_page_list.get(i);
                //System.out.println(ali.getPageID()+" "+ali.getPageRank());
            }

            Collections.sort(word_page_list,new RankComparator());
             System.out.println(count);
                try (BufferedWriter out = new BufferedWriter(new FileWriter(filetosort));) {
                    for (int i = 0; i<length; i++) {
                        word_page_list.add(new WordPage(word_page_data[i]));
                        WordPage ali = word_page_list.get(i);
                        out.write(ali.toString());
                        //System.out.println(ali.toString());
                        out.newLine();
                    }
                }
                    catch(FileNotFoundException fne)
                            {
                            fne.printStackTrace();
                            }
                    //FileWriter writer = new FileWriter();
                
            }
        }
}