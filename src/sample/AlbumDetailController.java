package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AlbumDetailController implements Initializable {

    //Fields
    private UserDetail user;
    private AlbumDetail album;

    //BUTTONS
    @FXML
    private Button logoutButton;

    @FXML
    private Button backButton;

    @FXML
    private Button addPhotoButton;

    @FXML
    private GridPane albumViewGridPane;

    @FXML
    private Label albumNameLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        /*
            So when loading the pictures of the images in, run a version of this through a for loop running through each picture.
            Set the url of that picture to a file and convert it to an image and set to i,i in gridpane
         */

        Image image = new Image("/Image/targaryen.png");
        ImageView pic = new ImageView();
        pic.setFitWidth(70);
        pic.setFitHeight(70);
        pic.setImage(image);
        albumViewGridPane.add(pic, 0,0);
    }

    //On Click Methods
    @FXML
    private void logOutPressed(ActionEvent e) throws IOException {
        System.out.println("Logging out from albums main screen");

        Stage stage = null;
        Parent root = null;

        if(e.getSource() == logoutButton){
            stage = (Stage) logoutButton.getScene().getWindow();

            root = FXMLLoader.load(getClass().getResource("loginScreen.fxml"));
        }

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

    }

    @FXML
    private void backPressed(ActionEvent e) throws IOException {
        System.out.println("Logging out from albums main screen");

        Stage stage = null;
        Parent root = null;
        FXMLLoader loader = new FXMLLoader();

        if(e.getSource() == backButton){
            stage = (Stage) backButton.getScene().getWindow();
            loader.setLocation(getClass().getResource("albumsMainScreen.fxml"));
            root = loader.load();
            AlbumsMainController next = loader.getController();
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void addPhotoPressed(ActionEvent e) throws IOException{

       Stage stage = null;
        Parent root = null;

        if(e.getSource() == addPhotoButton){
            stage = (Stage) addPhotoButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("addPhotoScreen.fxml"));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    public void addPhotoToAlbum(PhotoDetail photo){
        System.out.println("Photo passed to Album screen: " + photo.filePathLocal + " caption : " + photo.caption + " date: " + photo.time);

        //NOW MUST ADD PHOTO TO ALBUM
        //System.out.println("Current Album : " + currentAlbum.name);
//      currentAlbum.addPhoto(photo);
//        System.out.println("added photo");
//
//        for(int i = 0; i < currentAlbum.photos.size(); i++){
//            System.out.println(currentAlbum.photos.get(i).caption);
//        }
    }

    public void setAlbumAndUser(UserDetail user, AlbumDetail album){
        this.user = user;
        this.album = album;
        albumNameLabel.setText(album.toString());
    }




}
