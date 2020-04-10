package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AlbumDetailController {

    //BUTTONS
    @FXML
    private Button logoutButton;

    @FXML
    private Button backButton;

    @FXML
    private Button addPhotoButton;

    //On Click Methods
    @FXML
    private void logOutPressed(ActionEvent e) throws IOException {
        System.out.println("Logging out from albums main screen");

        Stage stage = null;
        Parent root = null;

        if(e.getSource() == logoutButton){
            stage = (Stage) logoutButton.getScene().getWindow();

            root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        }

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

    } @FXML
    private void backPressed(ActionEvent e) throws IOException {
        System.out.println("Logging out from albums main screen");

        Stage stage = null;
        Parent root = null;

        if(e.getSource() == backButton){
            stage = (Stage) backButton.getScene().getWindow();

            root = FXMLLoader.load(getClass().getResource("albumsMainScreen.fxml"));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }




}
