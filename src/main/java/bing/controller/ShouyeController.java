package bing.controller;

import bing.Main;
import bing.ShouyeMain;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

//主程序的首页
public class ShouyeController implements Initializable{
    @FXML
    private ImageView serialport;
    @FXML
    private ImageView mesh;
    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private Hyperlink shouye;
    @FXML
    private Hyperlink GHdingdan;
    @FXML
    private Hyperlink CGdingdan;
    @FXML
    private Button logout;
    @FXML
    private Label userNAME;
    @FXML private  Label time;
    @FXML
    private void handleShowView(ActionEvent e) throws IOException {
        String view = (String) ((Node) e.getSource()).getUserData();
        loadFXML(view);
    }
    private void loadFXML(String view) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(view));
        mainBorderPane.setCenter(loader.load());
    }
    public void userNAME() {
        Controller controller = new Controller();
        List<String> user = controller.list;
        for (String a : user) {
            userNAME.setText("账号: "+a);
        }
    }
    //隔1秒判断一次有没有窗口接入
    public void serialport(){
        Image image1 = new Image("/images/ck.png");
        Image image2 = new Image("/images/nock.png");
        Timer timer1 = new Timer();
        timer1.scheduleAtFixedRate(new TimerTask() {
            synchronized  public void run() {
                ChaunkouController chaunkouController = new ChaunkouController();
                ArrayList<String> list =  chaunkouController.findPorts();
        if (list.size() > 0) {
            serialport.setImage(image1);
        }else{
            serialport.setImage(image2);
        }
            }
        }, 1000, 10000);
    }
    //每一秒都想百度抓包判断有无网络连接
public void mesh() {
    Image image1 = new Image("/images/Intel.png");
    Image image2 = new Image("/images/noInteger.png");
    URL url = null;
    Timer timer = new Timer();
    timer.scheduleAtFixedRate(new TimerTask() {
        synchronized   public void run() {
                URL url = null;
                try {
                    url = new URL("http://baidu.com/");
                    InputStream in = url.openStream();
                    mesh.setImage(image1);
                } catch (Exception e) {
                    mesh.setImage(image2);
                }
        }
    }, 1000, 1000);
}
    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //获取当前时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String res = simpleDateFormat.format(new Date());
        time.setText("登陆时间为: "+res);
        //去掉下划线
       shouye.setUnderline(false);
       GHdingdan.setUnderline(false);
       CGdingdan.setUnderline(false);
                serialport();//检查是否有串口存在
                mesh();//检查网络是否连接
                csshyperlinkbutton();
                userNAME();
        }
        //菜单选择鼠标点击时的颜色变换
    public void csshyperlinkbutton(){
        //初始化
        GHdingdan.setStyle("-fx-background-color: #00bcff");
        shouye.setOnMouseClicked(event -> {
            shouye.setStyle("-fx-background-color: #00bcff");
            GHdingdan.setStyle("");
            CGdingdan.setStyle("");
        });
        GHdingdan.setOnMouseClicked(event -> {
            GHdingdan.setStyle("-fx-background-color: #00bcff");
            shouye.setStyle("");
            CGdingdan.setStyle("");
        });
        CGdingdan.setOnMouseClicked(event -> {
            CGdingdan.setStyle("-fx-background-color: #00bcff");
            GHdingdan.setStyle("");
            shouye.setStyle("");
        });
       /*鼠标悬停，移开
        //鼠标进入node
        shouye.setOnMouseEntered(event->{
            shouye.setStyle("-fx-background-color: #00bcff");
        });
        //鼠标退出node
        shouye.setOnMouseExited(event->{
            shouye.setStyle("");
        });
        GHdingdan.setOnMouseEntered(event->{
            GHdingdan.setStyle("-fx-background-color: #00bcff");
        });
        //鼠标退出node
        GHdingdan.setOnMouseExited(event->{
            GHdingdan.setStyle("");
        });
        CGdingdan.setOnMouseEntered(event->{
            CGdingdan.setStyle("-fx-background-color: #00bcff");
        });
        //鼠标退出node
        CGdingdan.setOnMouseExited(event->{
            CGdingdan.setStyle("");
        });*/

        //退出登录，关闭当前窗口，打开登陆窗口
        logout.setOnMouseClicked(event -> {
            Stage primaryStage = (Stage) logout.getScene().getWindow();
            ((Node) (event.getSource())).getScene().getWindow().hide();
            primaryStage.close();
            Main we = new Main();//调用新的窗口类
            Stage stage1 = new Stage();
            try {
                we.start(stage1);//打开新窗口
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
