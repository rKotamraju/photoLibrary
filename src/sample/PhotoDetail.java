package sample;

import java.util.ArrayList;

public class PhotoDetail {
    ArrayList<String> tags;
    String caption;
    String filePathLocal;
    int date;

    public PhotoDetail(String caption, ArrayList<String> tags, String filePath){
        this.caption = caption;
        this.tags = tags;
        this.filePathLocal = filePath;
    }

   /*public PhotoDetail(String filePath){
        this.filePathLocal = filePath;
    }*/

    public String toString(){
        return filePathLocal + " caption: " + caption;
    }

}
