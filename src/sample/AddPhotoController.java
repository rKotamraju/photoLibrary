package sample;

import javafx.application.Application;
import javafx.beans.value.ObservableListValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;

public class AddPhotoController implements Initializable{

//Fields
    private UserDetail user;
    private AlbumDetail album;

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
    @FXML
    private Button addTageButton;

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

    //ComboBox
    @FXML
    private ComboBox<String> tagTypeComboBox;

    //Image path
    String path;

    //Observable List
    ObservableList<String> tagTypeOptions = FXCollections.observableArrayList();

    //add tags
    HashMap<String,String> listOfTags = new HashMap<String,String>();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       addPhotoButton.setDisable(true);
       tagTypeOptions.add("Location");
       tagTypeOptions.add("Person");
       tagTypeOptions.add("Color");
       tagTypeOptions.add("Add New Type");


       tagTypeComboBox.setItems(tagTypeOptions);

    }


    @FXML
    private void logOutPressed(ActionEvent e) throws IOException {
        System.out.println("Logging out from add Photo screen");

        UsersList.getInstance().writeApp();

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

        UsersList.getInstance().writeApp();

        Stage stage = null;
        Parent root = null;
        FXMLLoader loader = new FXMLLoader();

        if(e.getSource() == backButton){
            stage = (Stage) backButton.getScene().getWindow();
            loader.setLocation(getClass().getResource("albumDetailScreen.fxml"));
            root = loader.load();
            AlbumDetailController next = loader.getController();
            next.setAlbumAndUser(user, album);
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
        File myFile = new File(path);
        Image myImage = new Image(myFile.toURI().toString());

        System.out.println("About to set ImageView");

        photoImageView.setImage(myImage);

        addPhotoButton.setDisable(false);
    }

    @FXML
    private void addPhotoPressed(ActionEvent e) throws IOException {
        String caption = captionsTextField.getText();
        String[] tags = tagsTextField.getText().split(",");

        String photoPath = path;
        File myFile = new File(path);

        //Save Time/Date of Image
        Time timeOfPicture = new Time(myFile.lastModified());
        System.out.println("Date: " + timeOfPicture); //hour, minute, second format

        //Create photo object
        PhotoDetail photo = new PhotoDetail(caption, listOfTags, photoPath, timeOfPicture);
        //System.out.println("Photo added : " + photo.filePathLocal + " caption : " + photo.caption + " date: " + photo.time);
        System.out.println(Arrays.asList(listOfTags));


        //Bring Back to Album and add to album

        FXMLLoader loader = new FXMLLoader((getClass().getResource("albumDetailScreen.fxml")));
        Parent root = (Parent) loader.load();
        AlbumDetailController alb = loader.getController();
        alb.addPhotoToAlbum(photo);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();


    }

    @FXML
    private void pickStockPressed(ActionEvent e) throws IOException {

        Stage stage = null;
        Parent root = null;

        if(e.getSource() == pickStockButton){
            stage = (Stage) pickStockButton.getScene().getWindow();

            root = FXMLLoader.load(getClass().getResource("stockPhotos.fxml"));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void setStockPhoto(String subject){
        String tempPath;
        if(subject.equals("cat")){
            System.out.println("Cat selected");
            tempPath = "/Image/cat_caviar.jpg";
        }
        else if(subject.equals("dog")){
            System.out.println("Dog selected");
            tempPath = "/Image/puppy.jpeg";
        }
        else if(subject.equals("chocolate")){
            System.out.println("Chocolate selected");
            tempPath = "/Image/chocolate.jpg";
        }
        else if(subject.equals("family")){
            System.out.println("Family selected");
            tempPath= "/Image/family.jpeg";
        }
        else if(subject.equals("baby")){
            System.out.println("Baby selected");
            tempPath = "/Image/happy.jpg";
        }
        else{
            System.out.println("Flower selected");
            tempPath = "/Image/flower.jpg";
        }

        path = tempPath;

        Image myImage = new Image(tempPath);

        System.out.println("About to set ImageView");

        photoImageView.setImage(myImage);

        addPhotoButton.setDisable(false);
    }
    @FXML
    private void addTagPressed(ActionEvent e){
        System.out.println(tagsTextField.getText());
        System.out.println(tagTypeComboBox.getSelectionModel().getSelectedItem());
        if(!(tagTypeComboBox.getSelectionModel().getSelectedItem().equals("Add New Type"))){
            listOfTags.put(tagTypeComboBox.getSelectionModel().getSelectedItem(),tagsTextField.getText());
        }

    }

    @FXML
    private void tagTypeSelected(ActionEvent e){
        if(tagTypeComboBox.getSelectionModel().getSelectedItem().equals("Add New Type")){
            TextInputDialog newTag = new TextInputDialog();
            newTag.setHeaderText("Add New Tag Type");
            newTag.showAndWait();
            tagTypeOptions.add(newTag.getResult());
            listOfTags.put(newTag.getResult(),tagsTextField.getText());
        }
    }

    public void setAlbumAndUser(UserDetail user, AlbumDetail album){
        this.user = user;
        this.album = album;
    }

}
