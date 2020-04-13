package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.EOFException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginScreenController implements Initializable {

    private UsersList usersList;
    static final long serialVersionUID = 1L;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Button loginButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            usersList = UsersList.readApp();
        } catch (EOFException e){
            System.out.println("No users");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    @FXML
    private void loginPressed(ActionEvent e) throws IOException {
        System.out.println("Login Button Pressed");
        
        Stage stage = null;
        Parent root = null;
        FXMLLoader loader = new FXMLLoader();

        String usernameText = usernameTextField.getText();

        UserDetail u = usersList.getUser(usernameText);
        
        if(e.getSource() == loginButton){
            stage = (Stage) loginButton.getScene().getWindow();
            if(usernameText.compareTo("admin") == 0){
                loader.setLocation(getClass().getResource("adminScreen.fxml"));
                root = loader.load();
            }else if(usersList.checkName(usernameText)){
                loader.setLocation(getClass().getResource("albumsMainScreen.fxml"));
                root = loader.load();
                AlbumsMainController next = loader.getController();
                next.setUser(usersList.getUser(usernameText));
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Incorrect Username or Password!", ButtonType.OK);
                alert.showAndWait();
                return;
            }

        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



}