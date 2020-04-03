package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginScreenController {

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Button loginButton;

    @FXML
    private void loginPressed(ActionEvent e) throws IOException {
        System.out.println("Login Button Pressed");
        
        Stage stage = null;
        Parent root = null;
        
        if(e.getSource() == loginButton){
            stage = (Stage) loginButton.getScene().getWindow();
            if(usernameTextField.getText().compareTo("admin") == 0){
                root = FXMLLoader.load(getClass().getResource("secondScene.fxml"));
            }else{
                root = FXMLLoader.load(getClass().getResource("albumsMainScreen.fxml"));
            }

        }
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



}