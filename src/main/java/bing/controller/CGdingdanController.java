package bing.controller;

import bing.WeighMain;
import bing.common.Page;
import bing.jdbc.Mysqlservice;
import bing.model.Billionaire;
import bing.service.BillionaireServiceCG;
import bing.xqCGMain;
import bing.xqMain;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

//采购订单Controller
public class CGdingdanController implements Initializable {

    private static final Logger logger = Logger.getLogger(CGdingdanController.class);
    private static final ObservableList<Billionaire> tablesa = FXCollections.observableArrayList();

    @FXML
    private Pagination pager;
    @FXML
    private VBox tableBox;
    @FXML
    private TableView<Billionaire> table;
    @FXML
    private TableColumn<Billionaire, Integer> id;
    @FXML
    private TableColumn<Billionaire, String> name;
    @FXML
    private TableColumn<Billionaire, String> type;
    @FXML
    private TableColumn<Billionaire, Integer> amount;
    @FXML
    private TableColumn<Billionaire, Integer> state;
    @FXML
    private TableColumn<Billionaire, String> newtime;
    @FXML
    private TableColumn<Billionaire, Integer> phone;
    @FXML
    private TableColumn xiangqing;
    @FXML
    private Button button;
    @FXML
    private TextField searchID;
    @FXML
    private Button searchBtn;
    @FXML
    private ContextMenu itemContextMenu;
    @FXML
    private MenuItem removeItem;
    @FXML
    private TextField searchName;
    @FXML
    private ComboBox searchType;
    @FXML
    private ComboBox searchStat;
    @FXML
    private DatePicker searchkaishitime;
    @FXML
    private DatePicker searchjieshutime;
    @FXML
    private Button button2;

    private boolean init = true;
    private Page<Billionaire> page = new Page<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> serialPortItems = FXCollections.observableArrayList();
        serialPortItems.addAll("");
        serialPortItems.addAll("195");
        serialPortItems.addAll("71");
        serialPortItems.addAll("128");
        serialPortItems.addAll("168");
        serialPortItems.addAll("174");
        searchType.setItems(serialPortItems);

        ObservableList<String> statcombobox = FXCollections.observableArrayList();
        statcombobox.addAll("");
        statcombobox.addAll("0");
        statcombobox.addAll("1");
        statcombobox.addAll("2");
        statcombobox.addAll("3");
        statcombobox.addAll("4");
        statcombobox.addAll("5");
        searchStat.setItems(statcombobox);

        shoelist();
        configurePager();
        try {
            search();
        } catch (Exception e) {
            e.printStackTrace();
        }
        configureTable();
        Platform.runLater(() -> searchBtn.requestFocus());
        BillionaireServiceCG shuju = null;
        try {
            shuju = new BillionaireServiceCG();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tablesa.addAll(shuju.BILLIONAIRES);
        itemContextMenu.setOnShowing(event -> {
            Billionaire billionaire = table.getSelectionModel().getSelectedItem();
            removeItem.setDisable(false);
        });
        //双击
        table.setRowFactory(tv -> {
            TableRow<Billionaire> row = new TableRow<Billionaire>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Billionaire emailInfo = row.getItem();
                    Mysqlservice mysqlservice = new Mysqlservice();
                    try {
                        ResultSet rs = mysqlservice.selectindexdingdanCG(emailInfo.getId());
                        XQcontrollerCG indexprodect = new XQcontrollerCG();
                        indexprodect.shujuindex(rs);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    //双击事件
                    try {
                        xqCGMain xqmain = new xqCGMain();//调用新的窗口类
                        Stage stage = new Stage();
                        xqmain.start(stage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
    }
    /**
     * search
     */
    public void search() throws Exception {
        init = false;
        page.setPageNo(1);
        pager.setPageCount(1);
        pager.setCurrentPageIndex(0);
        String id = this.searchID.getText();
        String name = this.searchName.getText();
        String type = (String) this.searchType.getValue();
        String stat = (String) this.searchStat.getValue();
        //转换开始日期时间为时间戳
        LocalDate begindate = this.searchkaishitime.getValue();
        String begintime = begindate+" 00:00:00";
        long kaishitime = dateToStamp(begintime);

        //转换结束日期时间为时间戳
        LocalDate overdate = this.searchjieshutime.getValue();
        String overtime = overdate+" 23:59:59";
        long  jieshutime ;
        if (dateToStamp(overtime)==0){
            jieshutime=99999999999999999L;
        }else{
            jieshutime=dateToStamp(overtime);
        }

        if (this.searchType.getValue() == null) {
            type = "";
        }
        if (this.searchStat.getValue() == null) {
            stat = "";
        }
        BillionaireServiceCG.instance().search(id, name, type, stat,kaishitime,jieshutime, page);

        table.setItems(FXCollections.observableArrayList(page.getData()));
        pager.setPageCount(page.getTotalPages());
    }

    //转换时间戳方法
    public static long dateToStamp(String s)  {
        String a="null 00:00:00";
        String b="null 23:59:59";
        if (s.equals(a) ||  s.equals(b)) {
            return 0;
        }else{
            //String res;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = null;
            try {
                date = simpleDateFormat.parse(s);
                long ts = date.getTime() / 1000;
                //res = String.valueOf(ts);
                return ts;
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return 0;
        }
    }

    /**
     * 删除
     */
    public void remove() throws SQLException {
        Billionaire billionaire = table.getSelectionModel().getSelectedItem();
        System.out.println(billionaire.getId());
        logger.info("remove: " + billionaire.toString());
        BillionaireServiceCG.instance().remove(billionaire);
        try {
            search();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 映射
     */
    private void configureTable() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        state.setCellValueFactory(new PropertyValueFactory<>("state"));
        newtime.setCellValueFactory(new PropertyValueFactory<>("newtime"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
    }

    /**
     * 设置分页事件
     */
    private void configurePager() {
        pager.setPageCount(1);
        pager.setCurrentPageIndex(0);
        pager.setPageFactory(pageIndex -> createPage(pageIndex));

    }

    /**
     * 刷新显示页面
     *
     * @param pageIndex
     * @return
     */
    public VBox createPage(int pageIndex) {
        page.setPageNo(pageIndex + 1);
        if (!init) {
            String id = this.searchID.getText();
            String name = this.searchName.getText();
            String type = (String) this.searchType.getValue();
            String stat = (String) this.searchStat.getValue();
            //转换开始日期时间为时间戳
            LocalDate begindate = this.searchkaishitime.getValue();
            String begintime = begindate+" 00:00:00";
            long kaishitime = dateToStamp(begintime);

            //转换结束日期时间为时间戳
            LocalDate overdate = this.searchjieshutime.getValue();
            String overtime = overdate+" 23:59:59";
            long  jieshutime ;
            if (dateToStamp(overtime)==0){
                jieshutime=99999999999999999L;
            }else{
                jieshutime=dateToStamp(overtime);
            }

            if (this.searchType.getValue() == null) {
                type = "";
            }
            if (this.searchStat.getValue() == null) {
                stat = "";
            }
            try {
                BillionaireServiceCG.instance().search(id, name, type, stat,kaishitime,jieshutime, page);
            } catch (Exception e) {
                e.printStackTrace();
            }
            table.getItems().clear();
            table.getItems().addAll(page.getData());
        }
        return tableBox;
    }


    //tableview中添加按钮
    public void shoelist() {
        xiangqing.setCellFactory((col) -> {
                    TableCell<Billionaire, String> cell = new TableCell<Billionaire, String>() {
                        @Override
                        protected void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            button2 = new Button("详情");
                            button2.setStyle("-fx-background-color: #00bcff;-fx-text-fill: #ffffff;-fx-alignment: center");
                            button2.setOnMouseClicked((col) -> {
                                try {
                                    Billionaire data = getTableView().getItems().get(getIndex());
                                    Mysqlservice mysqlservice = new Mysqlservice();
                                    ResultSet buttondata = mysqlservice.selectindexdingdanCG(data.getId());
                                    XQcontrollerCG xQcontrollerCG = new XQcontrollerCG();
                                    xQcontrollerCG.shujuindex(buttondata);
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                    logger.error("", e);
                                }
                                //按钮单击事件
                                try {
                                    //调用新的窗口类
                                    xqCGMain xqmain = new xqCGMain();
                                    Stage stage = new Stage();
                                    xqmain.start(stage);

                                } catch (IOException e) {
                                    logger.error("弹出新窗口Error:", e);
                                    e.printStackTrace();
                                }
                            });

                            if (empty) {
                                //如果此列为空默认不添加元素
                                setText(null);
                                setGraphic(null);
                            } else {
                                this.setGraphic(button2);
                            }
                        }
                    };
                    return cell;
                }
        );
    }
}
