/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsa.project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author AliHassaanM
 */
public class CombiningFiles {

    /**
     * @param args the command line arguments
     */
    public static void combinemain(String[] args) throws IOException {
        String directory = "D:\\Search Engine Java\\processeddata";
        String harisdir = "D:\\Search Engine Java1\\processeddata";
        File[] directoryFiles = new File(directory).listFiles();
        File[] harisFiles = new File(harisdir).listFiles();
        String harisfilename;
        int count = 0;
        for (File file: harisFiles){
            harisfilename = file.getName();
            //System.out.println(harisfilename);
            File haristextfile;
            String harisfilecontent = "";
            //Check if the file in haris's directory exists in Main Directory
            File check = new File(directory+"\\"+harisfilename);
            File directorytextfile;
            boolean success = check.mkdirs(); 
            if(check.exists() || success)
            {
                //If Yes then just open Haris's file and my file and append the content from haris's file after my file.
                try{  
                    haristextfile = new File(file.getAbsolutePath()+"\\documents.txt");
                    Scanner harisfilescanner = new Scanner(haristextfile);
                    
                    System.out.println(harisfilecontent);
                    try(PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(new File(directory+"\\"+harisfilename+"\\documents.txt"),true)));)                
                    {
                        while (harisfilescanner.hasNext()){
                        writer.println(harisfilescanner.nextLine());
                        }
                        count++;
                    }
                    catch(IOException ioe)
                    {
                    }
                }
                catch(FileNotFoundException fnfe)
                {
                    
                }
            }
            else {
            System.out.println("File Cannot be created or Exists");
            }
            System.out.println(harisfilename+" : "+count);
        }
            
        
    }
    
}
