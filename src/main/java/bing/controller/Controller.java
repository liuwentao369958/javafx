package bing.controller;

import bing.ShouyeMain;
import bing.jdbc.Mysqlservice;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//登陆界面
public class Controller {
    private static final Logger logger = Logger.getLogger(FxmlPagerSampleController.class.getClass());
    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private  Text tishi;
    @FXML private Button login;
 public static List<String> list = new ArrayList<>();
    //单击登陆按钮时间
    @FXML
    public void loginAction() throws SQLException {
        Mysqlservice mysqlControll = new Mysqlservice();
        ResultSet a = mysqlControll.selectuser();
        String uname = username.getText();
        String pawd = password.getText();
        if (null == uname || "".equals(uname)) {
            tishi.setText("请输入用户名和密码后登陆！");
        }
        boolean username = mysqlControll.selectusername(uname);
        String  userpawd = mysqlControll.selectuserpawd(uname);
        while (a.next()) {
            if (username == false) {
                tishi.setText("无效的用户名，请重新输入！");
                break;
            } else {
                if (null == pawd || "".equals(pawd)) {
                    tishi.setText("请输入密码，密码不能为空！");
                    break;
                }
                if (userpawd.equals(pawd)) {
                    tishi.setText("");
                    Stage primaryStage = (Stage) login.getScene().getWindow();//绑定按钮
                    list.add(uname);
                    primaryStage.close();//关闭当前的窗口
                    ShouyeMain we = new ShouyeMain();//调用新的窗口类
                    Stage stage = new Stage();
                    try {
                        we.start(stage);//打开新窗口
                        break;
                    } catch (Exception e) {
                        logger.error("userError:",e);
                        e.printStackTrace();
                    }
                } else {
                    tishi.setText("用户名或密码错误，请重新输入！");
                    break;
                }
            }
        }
    }
}
