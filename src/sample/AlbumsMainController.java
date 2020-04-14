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

//Fields
    private UserDetail user;
    //private UsersList usersList = UsersList.getInstance();
    static final long serialVersionUID = 1L;

//BUTTONS
    @FXML
    private Button logOutButton;

    @FXML
    private Button renameAlbumButton;
    @FXML
    private Button deleteAlbumButton;
    @FXML
    private Button createAlbumButton;
    @FXML
    private Button selectAlbumButton;
    @FXML
    private Button searchButton;

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
        renameAlbumButton.setDisable(true);
        deleteAlbumButton.setDisable(true);

        editMode = false;


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
    private void searchButtonPressed(ActionEvent e){
        System.out.println("Search Button Pressed");


    }


    @FXML
    private void goToAlbumDetailScreen() throws IOException{
        //ERROR: For some reason cannot click on listview with ActionEvent e as parameter

        if(editMode == false){
            AlbumDetail selectedAlbum = AlbumsListView.getSelectionModel().getSelectedItem();

            Stage stage = null;
            Parent root = null;
            FXMLLoader loader = new FXMLLoader();

            stage = (Stage) AlbumsListView.getScene().getWindow();

            loader.setLocation(getClass().getResource("albumDetailScreen.fxml"));
            root = loader.load();

            AlbumDetailController next = loader.getController();
            next.setAlbumAndUser(user, selectedAlbum);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }

    }

    public void isListViewEmpty(){
        if(albumsObservableList.size() == 0){
            AlbumsListView.setDisable(true);
        }
    }

    @FXML
    private void logOutPressed(ActionEvent e) throws IOException {
        System.out.println("Logging out from albums main screen");

        //saving data on albums
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
    private void selectPressed(ActionEvent e){
        editMode = true;

        renameAlbumButton.setDisable(false);
        deleteAlbumButton.setDisable(false);

        if(user.getAlbums().size() == 0){
            selectAlbumButton.setDisable(true);
        }


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
        user.addAlbum(newAlbum);

        selectAlbumButton.setDisable(false);
        editMode = false;
        AlbumsListView.setDisable(false);

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

        renameAlbumButton.setDisable(true);
        deleteAlbumButton.setDisable(true);
        selectAlbumButton.setDisable(false);
    }

    @FXML
    private void deleteAlbumPressed(ActionEvent e){

            //must press twice - first click delete to turn off listview then pick item and then press delete again

            AlbumDetail toBeDeleted = AlbumsListView.getSelectionModel().getSelectedItem();

            editMode = true;
            if(toBeDeleted == null){
                return;
            }

            ButtonType userChoice = ButtonType.NO;
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you want to delete this album?", ButtonType.CANCEL, ButtonType.YES);
            alert.showAndWait();
            userChoice = alert.getResult();
            if(userChoice == ButtonType.CANCEL) {
                return;
            }


            albumsObservableList.remove(toBeDeleted);
            user.removeAlbum(toBeDeleted);
            isListViewEmpty();

            System.out.println("Deleted");
            editMode = false;

            renameAlbumButton.setDisable(true);
            deleteAlbumButton.setDisable(true);
            selectAlbumButton.setDisable(false);
    }

    public void setUser(UserDetail user){
        this.user = user;
        albumsObservableList.addAll(user.getAlbums());

        if(user.getAlbums().size() == 0) {
            selectAlbumButton.setDisable(true);
            isListViewEmpty();
        }
    }



   /* @FXML
    private void searchingAlbums(ActionEvent e){
        String lookingFor = searchAlbumsTextField.getText();

    }*/

}
