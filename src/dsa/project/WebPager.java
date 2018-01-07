package dsa.project;
/**
 * @Author AliHassaanM
**/
final public class WebPager {
    private String title;
    private String text;
     private int id;

WebPager(int id,String title,String text){
    this.setId(id);
    this.setTitle(title);
    this.setText(text);
}
int getId(){
return this.id;
}
String getTitle(){
return this.title;
}
String getText(){
return this.text;
}
void setId(int id){
this.id = id;
}
void setTitle(String title){
this.title = title;
}
void setText(String text){
this.text = text;
}
public String toString() {
    return this.title + this.text;
}
}
