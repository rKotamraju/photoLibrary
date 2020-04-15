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

    //private UsersList usersList = UsersList.getInstance();
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
            UsersList.getInstance().readApp();
        } catch (EOFException e){
            System.out.println("No users");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if(UsersList.getInstance().getUsers().size() == 0){
            setUpStock();
            try {
                UsersList.getInstance().writeApp();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void setUpStock(){

        String str = "stock";
        String album = "Stock Album";

        UsersList.getInstance().addUser(new UserDetail(str));
        UsersList.getInstance().getUser(str).addAlbum(new AlbumDetail(album));
        UsersList.getInstance().getUser(str).getAlbum(album).addPhoto(new PhotoDetail("StockImage 1", "/Image/cat_caviar.jpg/", true));
        UsersList.getInstance().getUser(str).getAlbum(album).addPhoto(new PhotoDetail("StockImage 2", "/Image/chocolate.jpg/", true));
        UsersList.getInstance().getUser(str).getAlbum(album).addPhoto(new PhotoDetail("StockImage 3", "/Image/family.jpeg/", true));
        UsersList.getInstance().getUser(str).getAlbum(album).addPhoto(new PhotoDetail("StockImage 4", "/Image/flower.jpg/", true));
        UsersList.getInstance().getUser(str).getAlbum(album).addPhoto(new PhotoDetail("StockImage 5", "/Image/happy.jpg/", true));
        UsersList.getInstance().getUser(str).getAlbum(album).addPhoto(new PhotoDetail("StockImage 6", "/Image/puppy.jpeg/", true));
    }

    @FXML
    private void loginPressed(ActionEvent e) throws IOException {
        System.out.println("Login Button Pressed");
        
        Stage stage = null;
        Parent root = null;
        FXMLLoader loader = new FXMLLoader();

        String usernameText = usernameTextField.getText().trim();

        if(usernameText.length() == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Incorrect Username or Password!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        
        if(e.getSource() == loginButton){
            stage = (Stage) loginButton.getScene().getWindow();
            if(usernameText.compareTo("admin") == 0){
                loader.setLocation(getClass().getResource("adminScreen.fxml"));
                root = loader.load();
            }else if(UsersList.getInstance().checkName(usernameText)){
                loader.setLocation(getClass().getResource("albumsMainScreen.fxml"));
                root = loader.load();
                AlbumsMainController next = loader.getController();
                next.setUser(UsersList.getInstance().getUser(usernameText));
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