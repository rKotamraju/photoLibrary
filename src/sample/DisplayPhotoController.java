package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DisplayPhotoController implements Initializable {
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
    private void deletePressed(ActionEvent e){


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
}
