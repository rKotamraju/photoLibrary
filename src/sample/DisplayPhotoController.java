package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
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

    @FXML
    private Button deleteTagButton;

    @FXML
    private Button addTagButton;

    @FXML
    private Button recaptionButton;


    //Labels
    @FXML
    private Label captionLabel;

    @FXML
    private Label tagsLabel;

    @FXML
    private Label dateLabel;

    //ImageView
    @FXML
    private ImageView photoImageView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        turnOffEditing();

        System.out.println("Checking...");
        if(photo.getIsStock()){
            System.out.println("File Path : " + photo.getFilePathLocal());
            Image myImage = new Image(photo.getFilePathLocal());
            photoImageView.setImage(myImage);
        }
        else{
            String path = photo.getFilePathLocal();
            File myFile = new File(path);
            Image myImage = new Image(myFile.toURI().toString());
            photoImageView.setImage(myImage);
        }

        captionLabel.setText(photo.getCaption());
        dateLabel.setText(photo.getTime());

      // tagsLabel.setText(photo.ge);
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

        //Bring back to album screen
    }

    @FXML
    private void editPressed(ActionEvent e){
        deleteButton.setVisible(true);
        deleteButton.setDisable(false);

        saveButton.setDisable(false);
        saveButton.setVisible(true);

        addTagButton.setDisable(false);
        addTagButton.setVisible(true);

        deleteTagButton.setVisible(true);
        deleteTagButton.setDisable(false);

        recaptionButton.setDisable(false);
        recaptionButton.setVisible(true);
    }

    @FXML
    private void savePressed(ActionEvent e){

        //photo.setCaption();

        //at the end

       turnOffEditing();
    }

    @FXML
    private void recaptionPressed(ActionEvent e){
        TextInputDialog newLabel = new TextInputDialog();
        newLabel.setHeaderText("Enter New Caption");
        newLabel.showAndWait();
        captionLabel.setText(newLabel.getResult());
        photo.setCaption(captionLabel.getText());
        System.out.println(photo.getCaption());
        turnOffEditing();
    }

    @FXML
    private void addTagPressed(ActionEvent e){

    }

    public void setAlbumAndUserandPhoto(UserDetail user, AlbumDetail album, PhotoDetail photo){
        System.out.println("Hello heloo");
        this.user = user;
        this.album = album;
        this.photo = photo;
        System.out.println("Setting user and album of stock photo controller: " + this.album);
        // System.out.println(this.album);
    }

    public void turnOffEditing(){
        deleteButton.setDisable(true);
        deleteButton.setVisible(false);

        saveButton.setDisable(true);
        saveButton.setVisible(false);

        recaptionButton.setVisible(false);
        recaptionButton.setDisable(true);

        addTagButton.setDisable(true);
        addTagButton.setVisible(false);

        deleteTagButton.setVisible(false);
        deleteTagButton.setDisable(true);
    }
}
