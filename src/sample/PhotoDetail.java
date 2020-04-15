package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.Serializable;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class PhotoDetail implements Serializable {
    //private ImageView photo;

    static final long serialVersionUID = 1L;

    //private HashMap tags;

    private ArrayList<TagNode> tags;

    private String caption;
    private String filePathLocal;
    private String date;
    private boolean isStock;

    public PhotoDetail(String caption, ArrayList<TagNode> tags, String filePath, String date, boolean isStock){
        this.caption = caption;
        this.tags = tags;
        this.filePathLocal = filePath;
        this.date = date;
        this.isStock = isStock;
    }

    public PhotoDetail(String caption, String filePathLocal, boolean isStock){
        this.caption = caption;
        this.filePathLocal = filePathLocal;
        this.isStock = isStock;

    }

    public String getCaption(){

        return caption;
    }

    public String getFilePathLocal(){

        return filePathLocal;
    }

    public String getDate(){
        return date;
    }

    public boolean getIsStock(){

        return isStock;
    }

    public void setCaption(String caption){
        this.caption = caption;
    }


    public String toString(){
        return caption;
    }


    public ArrayList<TagNode>  getTags(){
        return tags;
    }

    public void removeTag(TagNode tag){
        tags.remove(tag);
    }

    public void addTag(TagNode tag){
        tags.add(tag);
    }



}
