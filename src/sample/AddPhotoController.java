package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddPhotoController implements Initializable{

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
    @FXML
    private ImageView photoImageView;

    //Labels
    @FXML
    private Label newPhotoLabel;

    //Image path

    String path;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       // addPhotoButton.setDisable(true);
      //  photoImageView = new ImageView();

      /*  Image image = new Image("/Image/targaryen.png");
        photoImageView.setImage(image);
*/
      /*  FileInputStream input = null;
        try {
            input = new FileInputStream("/Users/sujitmolleti/Desktop/Images/targaryen.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/
      //  Image image = new Image(input);
        //photoImageView = new ImageView(image);


       // System.out.println("Image loading error: " + image.isError());

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
    private void pickComputerPressed(ActionEvent e) throws IOException {
        System.out.println("Picking a picture from the computer");

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File fileChosen = fileChooser.showOpenDialog(pickComputerButton.getScene().getWindow());
        System.out.println("File Path :" + fileChosen.getAbsolutePath()); //THIS IS HOW TO GET THE PATH OF THE PICTURE SELECTED
        //THIS IS WHAT WE NEED TO SAVE! IN SERIALIZING

        //load photo into imageview
        path = fileChosen.getAbsolutePath();
       // File myFile = new File(path.);
        File myFile = new File(path);
        Image myImage = new Image(myFile.toURI().toString());

        System.out.println("About to set ImageView");
        photoImageView.setImage(myImage);
    }

    @FXML
    private void addPhotoPressed(ActionEvent e){
        String caption = captionsTextField.getText();
        String[] tags = tagsTextField.getText().split(",");
        ArrayList<String> listOfTags = new ArrayList<String>();
        for(int i = 0; i < tags.length;i++){
            listOfTags.add(tags[i]);
        }
        String photoPath = path;


    }

}
