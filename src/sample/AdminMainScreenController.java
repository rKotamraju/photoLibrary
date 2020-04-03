package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminMainScreenController {
    //Declaration of all buttons
     @FXML
        private Button logOutButton;

     @FXML
     private Button deleteUserButton;

     @FXML
     private Button createNewUserButton;

     //Declaration of ListView
    @FXML
    private ListView usersListView;


     @FXML
        private void logOutPressed(ActionEvent e) throws IOException {

         System.out.println("Logging out from Admin Main Screen");
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

}
