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
WordPage(int pageid,int index)//index 0 for Title 1 for Text
{
setPageId(pageid);
incrementCount(1,index);
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

}
