package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class StockPhotosController implements Initializable {
    //Buttons
    @FXML
    private Button addStockPhotoButton;

    @FXML
    private GridPane stockGridPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image dog = new Image("/Image/puppy.webp");
        Image happyBaby = new Image("/Image/happy.jpg");
        Image flower = new Image("/Image/flower.jpg");
        Image cat = new Image("Image/cat_caviar.jpg");
        Image targaryen = new Image("Image/targaryen.png");

        ImageView dogView = new ImageView();
        dogView.setFitHeight(50);
        dogView.setFitWidth(50);
        dogView.setImage(dog);

        ImageView babyView = new ImageView();
        babyView.setFitWidth(50);
        babyView.setFitHeight(50);
        babyView.setImage(happyBaby);

        ImageView flowerView = new ImageView();
        flowerView.setFitHeight(50);
        flowerView.setFitWidth(50);
        flowerView.setImage(flower);

        ImageView catView = new ImageView();
        catView.setFitHeight(50);
        catView.setFitWidth(50);
        catView.setImage(cat);

        ImageView targaryenView = new ImageView();
        targaryenView.setFitWidth(50);
        targaryenView.setFitHeight(50);
        targaryenView.setImage(targaryen);

        stockGridPane.add(dogView,1, 2);
        stockGridPane.add(babyView,1,0);
        stockGridPane.add(flowerView,0,1);
        stockGridPane.add(catView,1,1);
        stockGridPane.add(targaryenView,0,2);

    }
}
