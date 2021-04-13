package bing.controller;

import bing.jdbc.Mysqlservice;
import bing.model.Billionaire;
import bing.model.Dingdandao;
import bing.service.TimestampConvertsTime;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

//双击之后的界面--->查看详情--------------->供货订单
public class XQcontroller implements Initializable {
    @FXML
    private TextField xinaghao;
    @FXML
    private TextField ddhao;
    @FXML
    private TextField ddname;
    @FXML
    private TextField ddtype;
    @FXML
    private TextField ddtime;
    @FXML
    private TextField ddjiage;
    @FXML
    private TextField start;
    @FXML
    private TextField gonghuotime;
    @FXML
    private TextField gonghuodidian;
    @FXML
    private TextField caigoudian;
    @FXML
    private TextField faburen;
    @FXML
    private TextField gonghuofenlei;
    @FXML
    private TextField shuliang;
    @FXML
    private TextField danjia;
    @FXML
    private TextField zongjia;
    @FXML
    private TextField shijishuliang;
    @FXML
    private TextField shijidanjia;
    @FXML
    private TextField shijizongliang;
    @FXML
    private Button tuichu;
    @FXML
    private TextArea beizhu;

    private List<Dingdandao> list = new ArrayList<>();
    public static Dingdandao dingdandao = new Dingdandao();

    @FXML
    public void shujuindex(ResultSet indexdingdan) throws SQLException {
        while (indexdingdan.next()) {
            dingdandao.setId(indexdingdan.getInt("id"));
            dingdandao.setOrderno(indexdingdan.getString("orderno"));
            dingdandao.setOrdertype(indexdingdan.getInt("ordertype"));
            dingdandao.setUserid(indexdingdan.getInt("userid"));
            dingdandao.setTitle(indexdingdan.getString("title"));
            dingdandao.setCategory(indexdingdan.getString("category"));
            dingdandao.setThumb(indexdingdan.getString("thumb"));

            dingdandao.setAmount(indexdingdan.getDouble("amount"));
            dingdandao.setPrice(indexdingdan.getDouble("price"));
            dingdandao.setTotal(indexdingdan.getDouble("total"));

            dingdandao.setReplyamount(indexdingdan.getDouble("replyamount"));
            dingdandao.setReplyprice(indexdingdan.getDouble("replyprice"));
            dingdandao.setReplytotal(indexdingdan.getDouble("replytotal"));

            dingdandao.setStatus(indexdingdan.getInt("status"));
            dingdandao.setCreatetime(indexdingdan.getInt("createtime"));
            dingdandao.setSupplytime(indexdingdan.getString("supplytime"));
            dingdandao.setAddress(indexdingdan.getString("address"));
            dingdandao.setRemark(indexdingdan.getString("remark"));
            dingdandao.setPackger(indexdingdan.getString("package"));
            dingdandao.setSpecification(indexdingdan.getString("specification"));
            dingdandao.setPurchid(indexdingdan.getInt("purchid"));
        }

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
        TimestampConvertsTime timestampConvertsTime = new TimestampConvertsTime();
        ddhao.setText("" + dingdandao.getOrderno());
        ddname.setText(dingdandao.getTitle());
        ddtime.setText(timestampConvertsTime.timestamp(String.valueOf(dingdandao.getCreatetime())));
        ddtype.setText("" + dingdandao.getOrdertype());
        ddjiage.setText("" + dingdandao.getSpecification());
        start.setText("" + dingdandao.getStatus());
        gonghuotime.setText(dingdandao.getSupplytime());
        gonghuodidian.setText("" + dingdandao.getAddress());
        caigoudian.setText("" + dingdandao.getPurchid());
        faburen.setText("" + dingdandao.getUserid());
        gonghuofenlei.setText(dingdandao.getCategory());

        shuliang.setText("" + dingdandao.getAmount());
        danjia.setText("" + dingdandao.getPrice());
        zongjia.setText("" + dingdandao.getTotal());

        shijishuliang.setText("" + dingdandao.getReplyamount());
        shijidanjia.setText("" + dingdandao.getReplyprice());
        shijizongliang.setText("" + dingdandao.getReplytotal());

        xinaghao.setText(dingdandao.getPackger());
        beizhu.setText(dingdandao.getRemark());

        tuichu.setOnMouseClicked((col) -> {
            Stage primaryStage = (Stage) tuichu.getScene().getWindow();
            primaryStage.close();
        });
    }
}
