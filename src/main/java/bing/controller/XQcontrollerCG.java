package bing.controller;

import bing.jdbc.Mysqlservice;
import bing.model.Billionaire;
import bing.model.Cgdingdan;
import bing.model.Dingdandao;
import bing.service.BillionaireService;
import bing.service.BillionaireServiceCG;
import bing.service.TimestampConvertsTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

//双击之后的界面--->查看详情--------------->采购订单
public class XQcontrollerCG implements Initializable {
    private static final Logger logger = Logger.getLogger(FxmlPagerSampleController.class);
    @FXML
    private Label xinaghao;
    @FXML
    private Label ddhao;
    @FXML
    private Label ddname;
    @FXML
    private Label ddtime;
    @FXML
    private Label ddjiage;
    @FXML
    private ComboBox start;
    @FXML
    private Label gonghuotime;
    @FXML
    private Label gonghuodidian;
    @FXML
    private Label caigoudian;
    @FXML
    private Label faburen;
    @FXML
    private Label gonghuofenlei;
    @FXML
    private Label shuliang;
    @FXML
    private Label danjia;
    @FXML
    private Label zongjia;
    @FXML
    private Label shijishuliang;
    @FXML
    private Label shijidanjia;
    @FXML
    private Label shijizongliang;
    @FXML
    private Button tuichu;
    @FXML
    private Label beizhu;
    @FXML private Button queren;

    private List<Dingdandao> list = new ArrayList<>();
    public static Cgdingdan dingdandao = new Cgdingdan();

    @FXML
    public void shujuindex(ResultSet indexdingdan) {
        try {
            while (indexdingdan.next()) {
                dingdandao.setId(indexdingdan.getInt("id"));
                dingdandao.setOrderno(indexdingdan.getString("orderno"));
                dingdandao.setUid(indexdingdan.getInt("uid"));
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
        }catch (Exception e){
            e.printStackTrace();
            logger.error("error"+e);
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
        //状态初始选择
        ObservableList<String> statcombobox = FXCollections.observableArrayList();
        statcombobox.addAll("0");
        statcombobox.addAll("1");
        statcombobox.addAll("2");
        statcombobox.addAll("3");
        statcombobox.addAll("4");
        statcombobox.addAll("5");
        start.setItems(statcombobox);
        start.getSelectionModel().select(dingdandao.getStatus());

        //获取订单信息
        TimestampConvertsTime timestampConvertsTime = new TimestampConvertsTime();
        ddhao.setText("" + dingdandao.getOrderno());
        ddname.setText(dingdandao.getTitle());
        ddtime.setText(timestampConvertsTime.timestamp(String.valueOf(dingdandao.getCreatetime())));
        ddjiage.setText("" + dingdandao.getSpecification());
        // start.set("" + dingdandao.getStatus());
        gonghuotime.setText(dingdandao.getSupplytime());
        gonghuodidian.setText("" + dingdandao.getAddress());
        caigoudian.setText("" + dingdandao.getPurchid());
        faburen.setText("" + dingdandao.getUid());
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

        queren.setOnAction((col) ->{
               boolean xiugai =  new Mysqlservice().updatacgdingdan(dingdandao.getOrderno(),Integer.parseInt(start.getValue().toString()));
                if (xiugai){
                    //弹窗
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.titleProperty().set("修改状态");
                    alert.headerTextProperty().set("修改成功");
                    alert.showAndWait();
                    try {
                        BillionaireServiceCG.instance().BILLIONAIRES.clear();

                        Mysqlservice mysqlservice = new Mysqlservice();
                        ResultSet resultSet = mysqlservice.selectdingdanCG();
                        while (resultSet.next()){
                            String id = resultSet.getString("orderno");
                            String dingdanname = resultSet.getString("title");
                            String fenlei =resultSet.getString("category");
                            int shuliang = resultSet.getInt("amount");
                            int status = resultSet.getInt("status");
                            String time =new TimestampConvertsTime().timestamp(resultSet.getString("createtime"));
                            int phone = resultSet.getInt("purchid");
                            BillionaireServiceCG.instance().BILLIONAIRES.add(new Billionaire(id,dingdanname,fenlei,shuliang,status,time,phone));
                        }
                        new CGdingdanController();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    //修改失败
                }
        });
    }
}
