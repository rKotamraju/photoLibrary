package sample;

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

public class AlbumsMainScreenController implements Initializable{

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

//TEXTFIELDS
    @FXML
    private TextField searchAlbumsTextField;

//LISTVIEW
    @FXML
    private ListView AlbumsListView;

    final ObservableList<AlbumDetail> albumsObservableList = observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        editAlbumButton.setDisable(true);
        renameAlbumButton.setDisable(true);
        deleteAlbumButton.setDisable(true);

        System.out.println("Intitializing");
        AlbumsListView.setItems(albumsObservableList);
    }

//ONCLICK METHODS
    @FXML
    private void logOutPressed(ActionEvent e) throws IOException {
        System.out.println("Logging out from albums main screen");

        Stage stage = null;
        Parent root = null;

        if(e.getSource() == logOutButton){
            stage = (Stage) logOutButton.getScene().getWindow();

            root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private void createAlbumPressed(ActionEvent e){

        TextInputDialog td = new TextInputDialog();
        td.setHeaderText("Enter an Album Name.");
        td.showAndWait();

        if(td.getResult() == null){
            return;
        }

        String albumName = td.getResult().trim();
        if(albumName.length() == 0){
            return;
        }

        AlbumDetail newAlbum = new AlbumDetail(albumName);
        albumsObservableList.add(newAlbum);


    }

}
