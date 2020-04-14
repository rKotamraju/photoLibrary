package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static javafx.collections.FXCollections.observableArrayList;

public class AlbumDetailController implements Initializable {

    //Fields
    private UserDetail user;
    private AlbumDetail album;

    //BUTTONS
    @FXML
    private Button logoutButton;

    @FXML
    private Button backButton;

    @FXML
    private Button addPhotoButton;

    @FXML
    private Label albumNameLabel;

    @FXML
    private TableView<ImageDetail> albumTableView;

    @FXML
    private TableColumn<ImageDetail, String> column1;

    @FXML
    private TableColumn<ImageDetail, ImageView> column2;

    private ArrayList<ImageDetail> images;


    final ObservableList<ImageDetail> albumsObservableList = observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

//        PhotoDetail temp = new PhotoDetail("Birthday", "/Image/happy.jpg/", true);
//        PhotoDetail temp2 = new PhotoDetail("Cookies", "/Image/puppy.jpeg/", true);
//
//        ImageDetail i1 = new ImageDetail(temp);
//        ImageDetail i2 = new ImageDetail(temp2);

//        albumsObservableList.add(i1);
//        albumsObservableList.add(i2);
//        albumsObservableList.add(i1);
//        albumsObservableList.add(i2);

        images = new ArrayList<ImageDetail>();

        column2.setCellValueFactory(new PropertyValueFactory<>("caption"));
        column1.setCellValueFactory(new PropertyValueFactory<>("photo"));

        albumTableView.setItems(albumsObservableList);
        //albumTableView.getColumns().addAll(column1, column2);

    }

    //On Click Methods
    @FXML
    private void logOutPressed(ActionEvent e) throws IOException {
        System.out.println("Logging out from albums main screen");

        UsersList.getInstance().writeApp();

        Stage stage = null;
        Parent root = null;

        if(e.getSource() == logoutButton){
            stage = (Stage) logoutButton.getScene().getWindow();

            root = FXMLLoader.load(getClass().getResource("loginScreen.fxml"));
        }

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

    }

    @FXML
    private void backPressed(ActionEvent e) throws IOException {
        System.out.println("Going back to albums main screen");

        UsersList.getInstance().writeApp();

        Stage stage = null;
        Parent root = null;
        FXMLLoader loader = new FXMLLoader();

        if(e.getSource() == backButton){
            stage = (Stage) backButton.getScene().getWindow();
            loader.setLocation(getClass().getResource("albumsMainScreen.fxml"));
            root = loader.load();
            AlbumsMainController next = loader.getController();
            next.setUser(UsersList.getInstance().getUser(user.getUsername()));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void addPhotoPressed(ActionEvent e) throws IOException{

       Stage stage = null;
        Parent root = null;
        FXMLLoader loader = new FXMLLoader();

        if(e.getSource() == addPhotoButton){
            stage = (Stage) addPhotoButton.getScene().getWindow();
            loader.setLocation(getClass().getResource("addPhotoScreen.fxml"));
            root = loader.load();
            AddPhotoController next = loader.getController();
            next.setAlbumAndUser(user, album);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void pictureSelected(MouseEvent mouseEvent) throws IOException {
        System.out.println("picture selected");

        ImageDetail photoPicked = albumTableView.getSelectionModel().getSelectedItem();

        System.out.println(photoPicked.retrievePhoto().getFilePathLocal());

        Stage stage = null;
        Parent root = null;
        FXMLLoader loader = new FXMLLoader();

        stage = (Stage) albumTableView.getScene().getWindow();

        loader.setLocation(getClass().getResource("displayPhoto.fxml"));
        root = loader.load();

        DisplayPhotoController next = loader.getController();
        next.setAlbumAndUserandPhoto(user,album,photoPicked.retrievePhoto());

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    //public void addPhotoToAlbum(PhotoDetail photo){
        //System.out.println("Photo passed to Album screen: " + photo.filePathLocal + " caption : " + photo.caption + " date: " + photo.time);

        //NOW MUST ADD PHOTO TO ALBUM
        //System.out.println("Current Album : " + currentAlbum.name);
//      currentAlbum.addPhoto(photo);
//        System.out.println("added photo");
//
//        for(int i = 0; i < currentAlbum.photos.size(); i++){
//            System.out.println(currentAlbum.photos.get(i).caption);
//        }
   // }

//    @FXML
//    private void photoSelected(MouseEvent e){
//        System.out.println("Picture clicked in table view");
//    }

    public void setAlbumAndUser(UserDetail user, AlbumDetail album){
        this.user = user;
        this.album = album;
        albumNameLabel.setText(album.toString());

        for( PhotoDetail p : album.getPhotos() ){
            this.images.add(new ImageDetail(p));
        }

        albumsObservableList.addAll(images);

    }



}
