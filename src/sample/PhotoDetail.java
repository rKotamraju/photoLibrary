package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

public class PhotoDetail implements Serializable {
    //private ImageView photo;

    static final long serialVersionUID = 1L;

    private HashMap tags;
    private String caption;
    private String filePathLocal;
    private Time time;
    private boolean isStock;

    public PhotoDetail(String caption, HashMap tags, String filePath, Time time, boolean isStock){
        this.caption = caption;
        this.tags = tags;
        this.filePathLocal = filePath;
        this.time = time;
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

    public String getTime(){
        return time.toString();
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


    public HashMap getTags(){
        return tags;
    }

    public void removeTag(String tag){
        tags.remove(tag);
    }

    public void addTag(String tag, String tagType){
        tags.put(tagType, tag);
    }




}
