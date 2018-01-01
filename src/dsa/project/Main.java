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
          String patternStr = "([{:;=</>|,%'.*\"+#$_()\\[\\]-[0-9]}])";
          String replacementStr = "";
         
          // Compile regular expression
          Pattern pattern = Pattern.compile(patternStr);
         
          // Replace all occurrences of pattern in input
          Matcher matcher = pattern.matcher(WebPages[i].body);
          WebPages[i].body = matcher.replaceAll(replacementStr);
         }
          for (int i=0;i<20;i++)
              System.out.println(WebPages[i].title+"\n"+WebPages[i].body);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }   
}
class WebPage{
WebPage(String title,String body)
{
this.title = title; this.body = body;
}
String title;
String body;
public String toString() {
        return this.title + this.body;
    }
}

class UserHandler extends DefaultHandler {
   static WebPage WebPages[] = new WebPage[500];
   boolean bTitle = false;
   boolean bRevision = false;
   boolean bText = false;
   String title = "";
   int pagecount = 0;
   @Override
   public void startElement(
      String uri, String localName, String qName, Attributes attributes)
      throws SAXException {
      
      if (qName.equalsIgnoreCase("page")) {
      } else if (qName.equalsIgnoreCase("title")) {
         bTitle = true;
      }  else if (qName.equalsIgnoreCase("text")) {
         bText = true;
      }
   }

   @Override
   public void endElement(String uri, 
      String localName, String qName) throws SAXException {
      if (qName.equalsIgnoreCase("page")) {
         makeWebPage();
         characters.setLength(0);
         bText=false;
      } 
   }
    private final StringBuilder characters = new StringBuilder(64);
   @Override
    public void characters(char ch[], int start, int length) throws SAXException {
      
      if (bTitle) {
         title = (new String(ch,start,length));
         bTitle = false;
      } else if (bText) {
          characters.append(new String(ch,start,length));
      }
   }
    public void makeWebPage()
    {
        Main.WebPages[this.pagecount] = new WebPage(this.title,this.characters.toString());
        this.pagecount++;
    }
}