package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

public class PhotoDetail {
    private ImageView photo;
    private HashMap tags;
    private String caption;
    private String filePathLocal;
    private Time time;

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

    public ImageView getPhoto(){
        photo = new ImageView();
        //File myFile = new File(filePathLocal);
        Image myImage = new Image(filePathLocal);
        photo.setImage(myImage);

        photo.setFitHeight(150);
        photo.setFitWidth(150);

        return photo;
    }


    public String toString(){
        return caption;
    }

}
