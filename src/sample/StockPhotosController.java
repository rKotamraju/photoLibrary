package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StockPhotosController implements Initializable {
    //Buttons
    @FXML
    private Button addStockPhotoButton;

    //ImageViews
    @FXML
    private ImageView dogImageView;

    @FXML
    private ImageView catImageView;

    @FXML
    private ImageView chocolateImageView;

    @FXML
    private ImageView happyBabyImageView;

    @FXML
    private ImageView flowerImageView;

    @FXML
    private ImageView familyImageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image dog = new Image("/Image/puppy.jpeg");
        Image happyBaby = new Image("/Image/happy.jpg");
        Image flower = new Image("/Image/flower.jpg");
        Image cat = new Image("Image/cat_caviar.jpg");
        Image chocolate = new Image("Image/chocolate.jpg");
        Image family = new Image("/Image/family.jpeg");


        dogImageView.setImage(dog);

        happyBabyImageView.setImage(happyBaby);

        flowerImageView.setImage(flower);

        catImageView.setImage(cat);

        chocolateImageView.setImage(chocolate);

        familyImageView.setImage(family);
    }

    @FXML
    private void dogClicked(MouseEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader((getClass().getResource("addPhotoScreen.fxml")));
        Parent root = (Parent) loader.load();
        AddPhotoController alb = loader.getController();
        alb.setStockPhoto("dog");
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void catClicked(MouseEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader((getClass().getResource("addPhotoScreen.fxml")));
        Parent root = (Parent) loader.load();
        AddPhotoController alb = loader.getController();
        alb.setStockPhoto("cat");
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void familyClicked(MouseEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader((getClass().getResource("addPhotoScreen.fxml")));
        Parent root = (Parent) loader.load();
        AddPhotoController alb = loader.getController();
        alb.setStockPhoto("family");
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void babyClicked(MouseEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader((getClass().getResource("addPhotoScreen.fxml")));
        Parent root = (Parent) loader.load();
        AddPhotoController alb = loader.getController();
        alb.setStockPhoto("baby");
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void chocolateClicked(MouseEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader((getClass().getResource("addPhotoScreen.fxml")));
        Parent root = (Parent) loader.load();
        AddPhotoController alb = loader.getController();
        alb.setStockPhoto("chocolate");
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void flowerClicked(MouseEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader((getClass().getResource("addPhotoScreen.fxml")));
        Parent root = (Parent) loader.load();
        AddPhotoController alb = loader.getController();
        alb.setStockPhoto("flower");
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
