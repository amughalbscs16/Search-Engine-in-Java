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
import java.util.LinkedList;
import org.tartarus.martin.Stemmer;
public class Main {
    
   public static void main(String[] args) throws SAXException, ParserConfigurationException, IOException {
    try {
         File inputFile = new File("D:\\Search Engine Java\\Xml file\\simplewiki.txt");
         SAXParserFactory factory = SAXParserFactory.newInstance();
         SAXParser saxParser = factory.newSAXParser();
         UserHandler userhandler = new UserHandler();
         saxParser.parse(inputFile, userhandler);
    } catch (Exception e) {
         e.printStackTrace();
    }
   }   

}

