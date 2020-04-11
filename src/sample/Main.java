package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("loginScreen.fxml"));
        primaryStage.setTitle("My Photos");

        Scene loginScene = new Scene(root);

        primaryStage.setScene(loginScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
