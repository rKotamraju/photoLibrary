package sample;

import javafx.beans.Observable;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DisplayPhotoController implements Initializable {
    //Fields
    private UserDetail user;
    public AlbumDetail album;
    private PhotoDetail photo;

    //Buttons
    @FXML
    private Button logOutButton;

    @FXML
    private Button backButton;

    @FXML
    private Button editButton;

    @FXML
    private Button saveButton;


    @FXML
    private Button addTagButton;

    @FXML
    private Button prevPhotoButton;

    @FXML
    private Button nextPhotoButton;

    //ComboBox
    @FXML
    private ComboBox<String> photoChoicesChoiceBox;


    //Labels

    @FXML
    private Label dateLabel;

    //ImageView
    @FXML
    private ImageView photoImageView;

    //TextFields
    @FXML
    private TextField captionTextField;

    //ListViews
    @FXML
    private ListView tagsListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        turnOffEditing();
    }

    //Lists
    final ObservableList<String> tags = FXCollections.observableArrayList();
    //final ObservableList<String> tags;

    Boolean editMode;



    @FXML
    private void logOutPressed(ActionEvent e) throws IOException {
        editMode = false;
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


    private void deletePressed() throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this photo?", ButtonType.CANCEL, ButtonType.YES);
        alert.showAndWait();

        if(alert.getResult() == ButtonType.YES){
            album.removePhoto(photo);
            System.out.println("Photo was deleted from album");


            //Bring back to album screen
            Stage stage = null;
            Parent root = null;
            FXMLLoader loader = new FXMLLoader();

            stage = (Stage) photoChoicesChoiceBox.getScene().getWindow();

            loader.setLocation(getClass().getResource("albumDetailScreen.fxml"));
            root = loader.load();

            AlbumDetailController next = loader.getController();
            next.setAlbumAndUser(user,album);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    public void photoChoiceClicked(ActionEvent e) throws IOException {
        if(photoChoicesChoiceBox.getValue().equals("Delete Photo")){
            deletePressed();
        }

        else if(photoChoicesChoiceBox.getValue().equals("Move Photo")){

            TextInputDialog moveAlbumPrompt = new TextInputDialog();
            moveAlbumPrompt.setHeaderText("Which album do you want to move this picture to?'");
            moveAlbumPrompt.showAndWait();

            String moveAlbum = moveAlbumPrompt.getResult();

            AlbumDetail toAlbum = user.getAlbum(moveAlbum);

            toAlbum.addPhoto(photo);

            album.removePhoto(photo);

            Stage stage = null;
            Parent root = null;
            FXMLLoader loader = new FXMLLoader();

            stage = (Stage) photoChoicesChoiceBox.getScene().getWindow();

            loader.setLocation(getClass().getResource("albumDetailScreen.fxml"));
            root = loader.load();

            AlbumDetailController next = loader.getController();
            next.setAlbumAndUser(user,album);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

        else if(photoChoicesChoiceBox.getValue().equals("Copy Photo")){
            TextInputDialog moveAlbumPrompt = new TextInputDialog();
            moveAlbumPrompt.setHeaderText("Which album do you want to copy this picture to?'");
            moveAlbumPrompt.showAndWait();

            String moveAlbum = moveAlbumPrompt.getResult();

            AlbumDetail toAlbum = user.getAlbum(moveAlbum);

            toAlbum.addPhoto(photo);
        }
    }

    @FXML
    private void editPressed(ActionEvent e){
        turnOnEditing();
    }

    @FXML
    private void savePressed(ActionEvent e){

        //photo.setCaption();

        //at the end

        String newCaption = captionTextField.getText();
        photo.setCaption(captionTextField.getText());

       turnOffEditing();
    }


    @FXML
    private void addTagPressed(ActionEvent e) throws IOException {
        TextInputDialog newLabel = new TextInputDialog();
        newLabel.setHeaderText("Enter Format: 'New Tag,Tag Type'");
        newLabel.showAndWait();

        String tag = newLabel.getResult();

        String[] tagArray = tag.split(",");

        String newTag = tagArray[0];
        String tagType = tagArray[1];

        tags.add(newTag+"[" + tagType + "]");

        photo.addTag(new TagNode(tagType, newTag));

        //Save
        UsersList.getInstance().writeApp();
    }

    @FXML
    public void captionChanged(ActionEvent actionEvent) {
        if(editMode == true){
            System.out.println("Caption Being Edited");
            String newCaption = captionTextField.getText();
            photo.setCaption(captionTextField.getText());
        }

    }

    @FXML
    private void tagSelectedToDelete() throws IOException {
        if(editMode == true){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this tag?", ButtonType.CANCEL, ButtonType.YES);
            alert.showAndWait();

            if(alert.getResult() == ButtonType.YES) {
                System.out.println("Tag to be removed : " +tagsListView.getSelectionModel().getSelectedItem().toString());
                tags.remove(tagsListView.getSelectionModel().getSelectedItem()); //setting observable list

                String selectedCell = tagsListView.getSelectionModel().getSelectedItem().toString();
                String tag = selectedCell.substring(0, selectedCell.indexOf('['));
                String type = selectedCell.substring(selectedCell.indexOf('[')+1, selectedCell.indexOf(']'));
                TagNode deleteTag = new TagNode(type, tag);
                photo.removeTag(deleteTag);
//
//                int indexPicked = tagsListView.getSelectionModel().getSelectedIndex();
//                TagNode node = photo.getTags().get(indexPicked);
//                //TagNode node = new TagNode(tagType,tag)
//                photo.removeTag(node); // setting actual photo tags
               // photo.removeTag(tagsListView.getSelectionModel().getSelectedItem().toString());

                //Saving
                UsersList.getInstance().writeApp();
            }
        }
    }


    public void turnOffEditing(){
        editMode = false;
       // tagsListView.setDisable(true);
        //captionTextField.setDisable(true);
        photoChoicesChoiceBox.setDisable(true);
        photoChoicesChoiceBox.setVisible(false);

        saveButton.setDisable(true);
        saveButton.setVisible(false);

        addTagButton.setDisable(true);
        addTagButton.setVisible(false);

    }

    public void turnOnEditing(){
        editMode = true;
        //tagsListView.setDisable(false);
        //captionTextField.setDisable(false);
        photoChoicesChoiceBox.setDisable(false);
        photoChoicesChoiceBox.setVisible(true);

        saveButton.setDisable(false);
        saveButton.setVisible(true);

        addTagButton.setDisable(false);
        addTagButton.setVisible(true);

    }


    public void setAlbumAndUserandPhoto(UserDetail user, AlbumDetail album, PhotoDetail photo){
        // System.out.println("Hello heloo");
        this.user = user;
        this.album = album;
        this.photo = photo;
        System.out.println("Setting user and album of stock photo controller: " + this.album );
        System.out.println("Photo: " + this.photo.getFilePathLocal() + ", " + this.photo.getIsStock() + ", " + this.photo.getCaption());
        // System.out.println(this.album);

        photoImageView.setFitHeight(250);
        photoImageView.setFitWidth(250);
        if(this.photo.getIsStock()){
            System.out.println("File Path : " + this.photo.getFilePathLocal());
            Image myImage = new Image(this.photo.getFilePathLocal());
            photoImageView.setImage(myImage);
        }
        else{
            String path = this.photo.getFilePathLocal();
            File myFile = new File(path);
            Image myImage = new Image(myFile.toURI().toString());
            photoImageView.setImage(myImage);
        }

        captionTextField.setText(this.photo.getCaption());
        //captionTextField.setDisable(true);
        dateLabel.setText(this.photo.getDate());

        //System.out.println(photo.getTags().values());
        ArrayList<TagNode> temp = photo.getTags();
        TagNode t = null;
        for(int i = 0; i < temp.size();i++){
            t = temp.get(i);

            tags.add(t.getValue() + "[" + t.getTag() + "]");
        }
        //tags.addAll(photo.getTags());

        for(String iterator : tags){
            System.out.println(iterator);
        }

        tagsListView.setItems(tags);

        photoChoicesChoiceBox.getItems().add("Delete Photo");
        photoChoicesChoiceBox.getItems().add("Move Photo");
        photoChoicesChoiceBox.getItems().add("Copy Photo");
    }

    public void prevPhotoPressed(ActionEvent actionEvent) throws IOException {

        System.out.println("In prev photo");

        int currIndex = album.getPhotos().indexOf(photo);

        if(currIndex > 0){
            PhotoDetail prevPhoto = album.getPhotos().get(currIndex - 1);
            System.out.println("Prev Photo: " + prevPhoto.getCaption());

            Stage stage = null;
            Parent root = null;
            FXMLLoader loader = new FXMLLoader();

            stage = (Stage) prevPhotoButton.getScene().getWindow();

            loader.setLocation(getClass().getResource("displayPhoto.fxml"));
            root = loader.load();

            DisplayPhotoController next = loader.getController();
            next.setAlbumAndUserandPhoto(user,album, prevPhoto);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else{
            return;
        }

    }

    public void nextPhotoPressed(ActionEvent actionEvent) throws IOException {
        System.out.println("In next photo");

        int currIndex = album.getPhotos().indexOf(photo);

        if(currIndex < album.getPhotos().size()){
            PhotoDetail nextPhoto = album.getPhotos().get(currIndex + 1);
            System.out.println("Next Photo: " + nextPhoto.getCaption());

            Stage stage = null;
            Parent root = null;
            FXMLLoader loader = new FXMLLoader();

            stage = (Stage) nextPhotoButton.getScene().getWindow();

            loader.setLocation(getClass().getResource("displayPhoto.fxml"));
            root = loader.load();

            DisplayPhotoController next = loader.getController();
            next.setAlbumAndUserandPhoto(user,album, nextPhoto);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else{
            return;
        }
    }
}
