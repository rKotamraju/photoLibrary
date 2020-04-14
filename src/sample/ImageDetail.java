package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class ImageDetail {

    private PhotoDetail p;
    private ImageView photo;
    private String filePathLocal;
    private String caption;

    public ImageDetail(PhotoDetail p){
        this.p = p;
        this.filePathLocal = p.getFilePathLocal();
        this.caption = p.getCaption();
    }

    public ImageView getPhoto(){
        if(p.getIsStock()){ //if Stock Photo
            photo = new ImageView();
            //File myFile = new File(filePathLocal);
            Image myImage = new Image(filePathLocal);
            photo.setImage(myImage);
        }

        else{
            photo = new ImageView();
            File myFile = new File(filePathLocal);
            Image myImage = new Image(myFile.toURI().toString());
            photo.setImage(myImage);
        }

        photo.setFitHeight(150);
        photo.setFitWidth(150);

        return photo;

    }

    public PhotoDetail retrievePhoto(){
        return p;
    }

    public String getCaption(){
        return this.caption;
    }

}
