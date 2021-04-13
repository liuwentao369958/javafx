package bing.controller;

import bing.model.Dingdanjilu;
import bing.service.TimestampConvertsTime;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
/*
* 订单记录点击详情的controller
*
*
* */
public class XQcontrollerWighjilu implements Initializable
{
    @FXML private TextField ddhao;
    @FXML private TextField ddname;
    @FXML private TextField  ddtype;
    @FXML private TextField type;
    @FXML private TextField jiesuanjiage;
    @FXML private TextField jiesuanweigth;
    @FXML private TextField caigoudian;
    @FXML private TextField stat;
    @FXML private TextField kuanghao;
    @FXML private TextField zhongzhitime;
    @FXML private TextField cjtime;
    @FXML private TextField shangshitime;
    @FXML private TextArea xinxishuoyuan;
    @FXML private Button coes;
    private static final Logger logger = Logger.getLogger(WeighingrecordController.class);
    private static Dingdanjilu dingdanjilu = new Dingdanjilu();
    public void showlist(ResultSet rs){
        try {
            while (rs.next()){
                dingdanjilu.setId(rs.getInt("id"));
                dingdanjilu.setOrderno(rs.getString("orderno"));
                dingdanjilu.setOperatid(rs.getInt("operatid"));
                dingdanjilu.setCreatetime(rs.getInt("createtime"));
                dingdanjilu.setStarttime(rs.getInt("starttime"));
                dingdanjilu.setMarkettime(rs.getInt("markettime"));
                dingdanjilu.setPurchid(rs.getInt("purchid"));
                dingdanjilu.setCatid(rs.getInt("catid"));
                dingdanjilu.setRealamount(rs.getInt("realamount"));
                dingdanjilu.setRealprice(rs.getInt("realprice"));
                dingdanjilu.setPackageno(rs.getString("packageno"));
                dingdanjilu.setType(rs.getInt("type"));
                dingdanjilu.setStatus(rs.getInt("status"));
                dingdanjilu.setInfo(rs.getString("info"));
            }

        }catch (Exception e){
            logger.error("",e);
            e.printStackTrace();
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
        ddhao.setText(dingdanjilu.getOrderno());
        ddname.setText(""+dingdanjilu.getOperatid());
        ddtype.setText(""+dingdanjilu.getCatid());
        type.setText(""+dingdanjilu.getType());
        jiesuanjiage.setText(""+dingdanjilu.getRealprice());
        jiesuanweigth.setText(""+dingdanjilu.getRealamount());
        caigoudian.setText(""+dingdanjilu.getPurchid());
        stat.setText(""+dingdanjilu.getStatus());
        kuanghao.setText(dingdanjilu.getPackageno());

        zhongzhitime.setText(timestampConvertsTime.timestamp(String.valueOf(dingdanjilu.getStarttime())));
        cjtime.setText(timestampConvertsTime.timestamp(String.valueOf(dingdanjilu.getCreatetime())));
        shangshitime.setText(timestampConvertsTime.timestamp(String.valueOf(dingdanjilu.getMarkettime())));
        xinxishuoyuan.setText(dingdanjilu.getInfo());

        coes.setOnMouseClicked(event-> {
            Stage primaryStage = (Stage) coes.getScene().getWindow();
            primaryStage.close();
        });
    }
}
