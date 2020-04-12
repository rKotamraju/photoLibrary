package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddPhotoController implements Initializable {

//BUTTONS
    @FXML
    private Button logOutButton;
    @FXML
    private Button backButton;
    @FXML
    private Button addPhotoButton;
    @FXML
    private Button pickComputerButton;
    @FXML
    private Button pickStockButton;

//TEXTFIELDS
    @FXML
    private TextField tagsTextField;
    @FXML
    private TextField captionsTextField;

//ImageView
    private ImageView photosImageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addPhotoButton.setDisable(true);
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

        if(e.getSource() == backButton){
            stage = (Stage) backButton.getScene().getWindow();

            root = FXMLLoader.load(getClass().getResource("albumDetailScreen.fxml"));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void pickComputerPressed(ActionEvent e) throws MalformedURLException {
        System.out.println("Picking a picture from the computer");

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File fileChosen = fileChooser.showOpenDialog(pickComputerButton.getScene().getWindow());
        System.out.println("File Path :" + fileChosen.getAbsolutePath()); //THIS IS HOW TO GET THE PATH OF THE PICTURE SELECTED
        //THIS IS WHAT WE NEED TO SAVE! IN SERIALIZING

        //load photo into imageview
       /* String imagePath = fileChosen.getAbsolutePath();
        FileInputStream fis = new FileInputStream(imagePath);
        Image myImage = new Image(fis);*/

       Image myImage = new Image(fileChosen.toURI().toURL().toExternalForm());
       photosImageView.setImage(myImage);


        //photosImageView.setImage(myImage);
        //photosImageView = new ImageView(myImage);
    }

}
