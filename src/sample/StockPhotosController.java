package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.awt.*;
import java.awt.event.ActionEvent;
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
        Image dog = new Image("/Image/puppy.jpeg");
        Image happyBaby = new Image("/Image/happy.jpg");
        Image flower = new Image("/Image/flower.jpg");
        Image cat = new Image("Image/cat_caviar.jpg");
        Image targaryen = new Image("Image/targaryen.png");

        ImageView dogView = new ImageView();
        dogView.setFitHeight(100);
        dogView.setFitWidth(130);
        dogView.setImage(dog);

        ImageView babyView = new ImageView();
        babyView.setFitWidth(130);
        babyView.setFitHeight(100);
        babyView.setImage(happyBaby);

        ImageView flowerView = new ImageView();
        flowerView.setFitHeight(100);
        flowerView.setFitWidth(130);
        flowerView.setImage(flower);

        ImageView catView = new ImageView();
        catView.setFitHeight(100);
        catView.setFitWidth(130);
        catView.setImage(cat);

        ImageView targaryenView = new ImageView();
        targaryenView.setFitWidth(100);
        targaryenView.setFitHeight(50);
        targaryenView.setImage(targaryen);

        stockGridPane.add(dogView,0, 0);
        stockGridPane.add(babyView,1,0);
        stockGridPane.add(flowerView,0,1);
        stockGridPane.add(catView,1,1);
        stockGridPane.add(targaryenView,0,2);
        stockGridPane.setAlignment(Pos.CENTER);

    }


    @FXML
    private void stockSelected(MouseEvent mouseEvent) {
        Node source = (Node)mouseEvent.getSource();
        System.out.println(source.toString());
//        int colIndex = stockGridPane.getColumnIndex(source);
//        int rowIndex = stockGridPane.getRowIndex(source);
//
//        System.out.println("STock selected, row: " + rowIndex + " , Colum : " + colIndex);
    }

}
