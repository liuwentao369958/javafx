package bing.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.ResourceBundle;

public class FrameupController implements Initializable {
    @FXML
    private HBox kuanghao;
    @FXML
    private Button buttonadd;
    @FXML
    private Button quxiao;
    @FXML
    private Button baocun;
    public  HashMap<Integer, TextField> hashMap = new HashMap<>();
    public  int key = 0;

    public TextField add() {
        TextField field1 = new TextField();
        field1.setPrefSize(48, 36);
        field1.setEditable(true);
        field1.setAlignment(Pos.CENTER_LEFT);
        field1.setStyle("-fx-font-size: 14px");
        field1.setPrefColumnCount(10);
        kuanghao.getChildren().add(field1);
        key++;
        hashMap.put(key, field1);
        return field1;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TextField field1 = new TextField();
        field1.setPrefSize(48, 36);
        field1.setEditable(true);
        field1.setAlignment(Pos.CENTER_LEFT);
        field1.setStyle("-fx-font-size: 14px");
        field1.setPrefColumnCount(10);
        field1.getText();
        kuanghao.getChildren().add(field1);
        hashMap.put(0, field1);


        buttonadd.setOnMouseClicked((col) -> {
            String s = hashMap.get(key).getText();
            if (null == s || "".equals(s) == false) {
                add();
            }
        });

        quxiao.setOnMouseClicked(event -> {
            Stage primaryStage = (Stage) quxiao.getScene().getWindow();
            primaryStage.close();
        });

        //点击保存
        baocun.setOnMouseClicked((col) -> {
        String string = "{";
            for (Iterator it = hashMap.entrySet().iterator(); it.hasNext(); ) {
                Entry e = (Entry) it.next();
                string += "'" + e.getKey() + "':";
                string += "'" + hashMap.get(e.getKey()).getText() + "',";
            }
            string = string.substring(0, string.lastIndexOf(","));
            string += "}";
            System.out.println(string);
            hashMap.clear();
            kuanghao.getChildren().clear();
            Stage primaryStage = (Stage) baocun.getScene().getWindow();
            primaryStage.close();
        });
    }

    public void dataindex(ResultSet a){//获得这一行的数据


    }
}

