package bing;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;


public class FrameupMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        URL location = ClassLoader.getSystemResource("frameup.fxml");
        Parent root = FXMLLoader.load(location);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setResizable(false);
        primaryStage.setFullScreen(false);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        //primaryStage.initModality(Modality.WINDOW_MODAL);//让页面置顶显示
        //primaryStage.setOpacity(0.9); //透明度
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
