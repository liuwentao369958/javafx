package bing;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

public class xqMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("供货订单详情");
        URL location = ClassLoader.getSystemResource("xiangqing.fxml");
        Parent root = FXMLLoader.load(location);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setResizable(false);
        primaryStage.setFullScreen(false);
        primaryStage.getIcons().add(new Image(
                getClass().getResourceAsStream("/images/logo.jpg")));
        primaryStage.show();
    }
}
