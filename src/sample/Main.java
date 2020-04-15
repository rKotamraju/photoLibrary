package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static Stage window;

    /**
     * Starts the program by loading the first screen - login
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{

        window = primaryStage;

        Parent root = FXMLLoader.load(getClass().getResource("loginScreen.fxml"));
        primaryStage.setTitle("My Photos");

        Scene loginScene = new Scene(root);

        primaryStage.setOnCloseRequest(e -> {
            e.consume();
            onCloseRequest();
        });

        primaryStage.setResizable(false);
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void onCloseRequest(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to close?", ButtonType.NO, ButtonType.YES);
        alert.showAndWait();

        if(alert.getResult() == ButtonType.YES){
            try {
                UsersList.getInstance().writeApp();
            } catch (IOException e) {
                e.printStackTrace();
            }
            window.close();
        }

        System.out.println("User Exited the app");
    }
}
