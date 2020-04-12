package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static javafx.collections.FXCollections.observableArrayList;

public class AdminController implements Initializable {

    protected UsersList usersList;

    //Buttons
     @FXML
     private Button logOutButton;
     @FXML
     private Button deleteUserButton;
     @FXML
     private Button createNewUserButton;

     //ListView
    @FXML
    private ListView<UserDetail> usersListView;

    ObservableList<UserDetail> usersObservableList = observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        deleteUserButton.setDisable(true);
        //System.out.println("Intitializing");
        //UserDetail stockUser = new UserDetail("stock");
        ///usersObservableList.add(stockUser);
        usersList = new UsersList();

        try {
            usersList = UsersList.readApp();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        usersObservableList = usersList.getUsers();

        usersListView.setItems(usersObservableList);

    }

     @FXML
        private void logOutPressed(ActionEvent e) throws IOException {

         System.out.println("Logging out from Admin Main Screen");

         //UsersList.writeApp(usersList);

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
    private void createUserPressed(ActionEvent e){
         TextInputDialog td = new TextInputDialog();
         td.setHeaderText("Enter a UserName");
         td.showAndWait();

         if(td.getResult() == null){
             return;
         }

         String username = td.getResult().trim();
         if(username.length() == 0){
             return;
         }

         UserDetail newUser = new UserDetail(username);
         usersList.getUsers().add(newUser);

         deleteUserButton.setDisable(false);
     }

     @FXML
    private void deleteUserPressed(ActionEvent e){
         if(usersListView.getSelectionModel().getSelectedItem() == null){
             return;
         }
         ButtonType userChoice = ButtonType.NO;
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you want to delete this user?", ButtonType.CANCEL, ButtonType.YES);
         alert.showAndWait();
         userChoice = alert.getResult();
         if(userChoice == ButtonType.CANCEL){
             return;
         }

         usersList.getUsers().remove(usersListView.getSelectionModel().getSelectedItem());
         System.out.println("Deleted");
     }
}
