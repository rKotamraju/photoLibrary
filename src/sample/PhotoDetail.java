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

    public PhotoDetail(String caption, HashMap tags, String filePath, Time time){
        this.caption = caption;
        this.tags = tags;
        this.filePathLocal = filePath;
        this.time = time;
    }

    public PhotoDetail(String caption, String filePathLocal){
        this.caption = caption;
        this.filePathLocal = filePathLocal;

    }

    public String getCaption(){
        return caption;
    }

    public String getFilePathLocal(){
        return filePathLocal;
    }


    public String toString(){
        return caption;
    }

    public boolean getIsStock(){
        return isStock;
    }

}
