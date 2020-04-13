package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.Initializable;


import java.io.IOException;
import java.util.ResourceBundle;
import java.net.URL;

import static javafx.collections.FXCollections.observableArrayList;

public class AlbumsMainController implements Initializable{

//BUTTONS
    @FXML
    private Button logOutButton;
    @FXML
    private Button editAlbumButton;
    @FXML
    private Button renameAlbumButton;
    @FXML
    private Button deleteAlbumButton;
    @FXML
    private Button createAlbumButton;
    @FXML
    private Button selectAlbumButton;

    //Editing mode
    boolean editMode;

//TEXTFIELDS
    @FXML
    private TextField searchAlbumsTextField;

//LISTVIEW
    @FXML
    private ListView<AlbumDetail> AlbumsListView;

    final ObservableList<AlbumDetail> albumsObservableList = observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        editAlbumButton.setDisable(true);
        renameAlbumButton.setDisable(true);
        deleteAlbumButton.setDisable(true);
        editAlbumButton.setVisible(false);
        renameAlbumButton.setVisible(false);
        deleteAlbumButton.setVisible(false);
        selectAlbumButton.setDisable(true);

        editMode = true; //on

        System.out.println("Intitializing");
        AlbumsListView.setItems(albumsObservableList);

        AlbumsListView.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(AlbumsListView.isFocused()){
                    System.out.println("No Error");

                }
            }
        });

    }

//ONCLICK METHODS
    @FXML
    private void goToAlbumDetailScreen() throws IOException{
        //ERROR: For some reason cannot click on listview with ActionEvent e as parameter

        if(editMode == false){
            AlbumDetail selectedAlbum = AlbumsListView.getSelectionModel().getSelectedItem();

            Stage stage = null;
            Parent root = null;

            stage = (Stage) AlbumsListView.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("albumDetailScreen.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

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
    private void selectPressed(ActionEvent e){
        editMode = true;

        selectAlbumButton.setVisible(false);
        renameAlbumButton.setVisible(true);
        deleteAlbumButton.setVisible(true);
        editAlbumButton.setVisible(true);

        selectAlbumButton.setDisable(true);
        renameAlbumButton.setDisable(false);
        deleteAlbumButton.setDisable(false);
        editAlbumButton.setDisable(false);


    }


    @FXML
    private void createAlbumPressed(){


        editMode = true;
        TextInputDialog td = new TextInputDialog();
        td.setHeaderText("Enter an Album Name.");
        td.showAndWait();

        //if the user presses cancel
        if(td.getResult() == null){
            return;
        }

        String albumName = td.getResult().trim();

        //if User tries to add an empty album
        if(albumName.length() == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR, "You must enter an album name!", ButtonType.OK);
            alert.showAndWait();
            createAlbumPressed();
        }

        AlbumDetail newAlbum = new AlbumDetail(albumName);
        albumsObservableList.add(newAlbum);

        editAlbumButton.setDisable(false);
        renameAlbumButton.setDisable(false);
        deleteAlbumButton.setDisable(false);
        selectAlbumButton.setDisable(false);
        editMode = false;

    }

    @FXML
    private void renameAlbumPressed(ActionEvent e){
        editMode = true;
        if(AlbumsListView.getSelectionModel().getSelectedItem() == null){
            return;
        }
        TextInputDialog inputName = new TextInputDialog();
        inputName.setHeaderText("Enter a new album name.");
        inputName.showAndWait();

        if(inputName.getResult() == null){
            return;
        }

        String newName = inputName.getResult().trim();
        
        AlbumDetail selectedAlbum =AlbumsListView.getSelectionModel().getSelectedItem();
        System.out.println("Current Name: " +selectedAlbum);
        System.out.println("New Name: "+newName);
        AlbumsListView.getSelectionModel().getSelectedItem().name = newName; //changes name behind the scenes but doesn't show up
        AlbumsListView.getItems().set(AlbumsListView.getSelectionModel().getSelectedIndex(), selectedAlbum); //shows up
        editMode = false;

        selectAlbumButton.setVisible(true);
        renameAlbumButton.setVisible(false);
        deleteAlbumButton.setVisible(false);
        editAlbumButton.setVisible(false);
        renameAlbumButton.setDisable(true);
        deleteAlbumButton.setDisable(true);
        editAlbumButton.setDisable(true);
        selectAlbumButton.setDisable(false);
    }

    @FXML
    private void deleteAlbumPressed(ActionEvent e){

            //must press twice - first click delete to turn off listview then pick item and then press delete again
            editMode = true;
            if(AlbumsListView.getSelectionModel().getSelectedItem() == null){
                return;
            }

            ButtonType userChoice = ButtonType.NO;
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you want to delete this album?", ButtonType.CANCEL, ButtonType.YES);
            alert.showAndWait();
            userChoice = alert.getResult();
            if(userChoice == ButtonType.CANCEL) {
                return;
            }


            albumsObservableList.remove(AlbumsListView.getSelectionModel().getSelectedItem());
            System.out.println("Deleted");
            editMode = false;

            selectAlbumButton.setVisible(true);
            renameAlbumButton.setVisible(false);
            deleteAlbumButton.setVisible(false);
            editAlbumButton.setVisible(false);
            renameAlbumButton.setDisable(true);
            deleteAlbumButton.setDisable(true);
            editAlbumButton.setDisable(true);
            selectAlbumButton.setDisable(false);

    }

   /* @FXML
    private void searchingAlbums(ActionEvent e){
        String lookingFor = searchAlbumsTextField.getText();

    }*/

}
