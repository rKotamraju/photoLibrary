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
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DisplayPhotoController implements Initializable {
    //Fields
    private UserDetail user;
    private AlbumDetail album;
    private PhotoDetail photo;

    //Buttons
    @FXML
    private Button logOutButton;

    @FXML
    private Button backButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button saveButton;

    //Labels
    @FXML
    private Label captionLabel;

    @FXML
    private Label tagsLabel;

    //ImageView
    @FXML
    private ImageView photoImageView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        deleteButton.setDisable(true);
        deleteButton.setVisible(false);

        saveButton.setDisable(true);
        saveButton.setVisible(false);

        if(photo.getIsStock() == true){
            Image myImage = new Image(photo.getFilePathLocal());
            photoImageView.setImage(myImage);
        }
        else{
            String path = photo.getFilePathLocal();
            File myFile = new File(path);
            Image myImage = new Image(myFile.toURI().toString());
            photoImageView.setImage(myImage);
        }
    }

    @FXML
    private void logOutPressed(ActionEvent e) throws IOException {
        System.out.println("Logging out from albums main screen");

        Stage stage = null;
        Parent root = null;

        if(e.getSource() == logOutButton){
            stage = (Stage) logOutButton.getScene().getWindow();

            root = FXMLLoader.load(getClass().getResource("loginScreen.fxml"));
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void backPressed(ActionEvent e) throws IOException {

//        Stage stage = null;
//        Parent root = null;
//
//        if(e.getSource() == backButton){
//            stage = (Stage) backButton.getScene().getWindow();
//
//            root = FXMLLoader.load(getClass().getResource("albumDetailScreen.fxml"));
//        }
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();

        Stage stage = null;
        Parent root = null;
        FXMLLoader loader = new FXMLLoader();

        stage = (Stage) backButton.getScene().getWindow();

        loader.setLocation(getClass().getResource("albumDetailScreen.fxml"));
        root = loader.load();

        AlbumDetailController next = loader.getController();
        next.setAlbumAndUser(user,album);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void deletePressed(ActionEvent e){

        album.removePhoto(photo);
        System.out.println("Photo was deleted from album");

        //at the end
        deleteButton.setDisable(true);
        deleteButton.setVisible(false);

        saveButton.setDisable(true);
        saveButton.setVisible(false);
    }

    @FXML
    private void editPressed(ActionEvent e){
        deleteButton.setVisible(true);
        deleteButton.setDisable(false);

        saveButton.setDisable(false);
        saveButton.setVisible(true);
    }

    @FXML
    private void savePressed(ActionEvent e){


        //at the end

        deleteButton.setDisable(true);
        deleteButton.setVisible(false);

        saveButton.setDisable(true);
        saveButton.setVisible(false);
    }

    public void setAlbumAndUserandPhoto(UserDetail user, AlbumDetail album, PhotoDetail photo){
        this.user = user;
        this.album = album;
        this.photo = photo;
        System.out.println("Setting user and album of stock photo controller: " + this.album);
        // System.out.println(this.album);
    }
}
