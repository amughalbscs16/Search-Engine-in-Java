/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsa.project;

/**
 *
 * @author AliHassaanM
 */
public class WordPage {
int pageid;
int count[] = {0,0};//0 for Title 1 for text
double pagerank = 0.0;
WordPage()
{
}
String title = "";
WordPage(String data)
{
    data.replaceAll(":\n","");
    String data1[] = data.split(",");
    pageid = Integer.parseInt(data1[0]);
    pagerank = Double.parseDouble(data1[1]);
    count[0] = Integer.parseInt(data1[2]);
    count[1] = Integer.parseInt(data1[3]);
    try{
    title = data1[4];
    }catch(ArrayIndexOutOfBoundsException i)
    {
        i.printStackTrace();
        title = "Not Found";
    }
}
WordPage(int pageid,int index)//index 0 for Title 1 for Text
{
setPageId(pageid);
}
double getPageRank(){
return this.pagerank;
}
void setPageRank(double pagerank)
{
this.pagerank = pagerank;
}
final void setPageId(int pageid)
{
    this.pageid = pageid;
}
void incrementCount(int howmuch, int index)
{
    count[index]+=howmuch;
}
int getCount(int index)
{
   return count[index]; 
}
int getPageID()
{
   return pageid; 
}
String getTitle()
{
    return title;
}
void incrementPageRank(double howmuch)
{
    this.pagerank += howmuch;
}
@Override
public String toString()
{
    return this.pageid+","+this.getPageRank()+","+this.getCount(0)+","+this.getCount(1)+","+this.getTitle()+":";
}
public String toStringDisplay()
{
    return "Page Id: "+this.pageid+" Weightage: "+this.getPageRank()+" Title Count: "+this.getCount(0)+" Body Count: "+this.getCount(1)+" Title: "+this.getTitle()+":";
}
}
