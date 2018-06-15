package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));

        primaryStage.setTitle("Pass Keeper");

        Image kayImage = new Image("/icons/Key1.png");
        primaryStage.getIcons().add(kayImage);

        primaryStage.setScene(new Scene(root, 694, 450));
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setOpacity(0.98);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
