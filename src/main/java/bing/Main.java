package bing;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("电子实时监测系统");
        URL location = ClassLoader.getSystemResource("logintime.fxml");
            Parent root = FXMLLoader.load(location);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.UTILITY);
            primaryStage.setResizable(false);
            primaryStage.setFullScreen(false);
                    primaryStage.getIcons().add(new Image(
                getClass().getResourceAsStream("/images/logo.jpg")));
            primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
