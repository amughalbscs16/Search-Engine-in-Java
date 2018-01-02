/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsa.project;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author AliHassaanM
 */
public class UserHandler extends DefaultHandler {
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
         makeWebPage();
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
    public void makeWebPage()
    {
        Main.WebPages[this.pagecount] = new WebPage(this.id,this.title.toLowerCase(),this.characters.toString().toLowerCase());
        this.pagecount++;
    }
}
