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
public class Word {
int pageid;
int count[] = {0,0};//0 for Title 1 for text
boolean available[] = {false,false};
Word(int pageid){
setPageId(pageid);
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
void setAvailable(int index, boolean available)
{
    this.available[index]=available;
}
boolean getAvailable(int index)
{
    return this.available[index];
}
}
