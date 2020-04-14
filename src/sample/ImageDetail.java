package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageDetail {


    //
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
        photo = new ImageView();
        //File myFile = new File(filePathLocal);
        Image myImage = new Image(filePathLocal);
        photo.setImage(myImage);

        photo.setFitHeight(150);
        photo.setFitWidth(150);

        return photo;

    }

    public String getCaption(){
        return this.caption;
    }

}
