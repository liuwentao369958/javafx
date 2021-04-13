package bing.service;

import bing.common.Page;
import bing.controller.Controller;
import bing.jdbc.Mysqlservice;
import bing.model.Billionaire;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.*;

/**
 * Billionaire Query Service
 *供货订单事务处理类
 * @author: IceWee
 * @date: 2019.1.2
 */
public final class BillionaireService{
    public Connection conn;
    public Statement  stmt;

    private static class BillionaireServiceHolder  {
        private static BillionaireService INSTANCE = null;

        static {
            try {
                INSTANCE = new BillionaireService();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
 /*while (resultSet.next()){
        list.add(new Member(resultSet.getString("username"),
                resultSet.getString("password"),
                resultSet.getString("fullName"),
                resultSet.getString("avatar"),
                resultSet.getLong("remainTime"),
                resultSet.getInt("stauts")));
    }*/
    /**
     * 查询
     */
    public  final List<Billionaire> BILLIONAIRES = new ArrayList<>(Arrays.asList());
    public  String username = null;
    public BillionaireService()throws Exception {
       /*根据用户名查找改用户发布的订单
        Controller controller = new Controller();
       List<String> sa = controller.list;
        for (String user : sa){
            username = user;
        }*/
        Mysqlservice mysqlservice = new Mysqlservice();
        ResultSet resultSet = mysqlservice.selectdingdan();
        while (resultSet.next()){
            String id = resultSet.getString("orderno");
            String dingdanname = resultSet.getString("title");
            String fenlei =resultSet.getString("category");
            int shuliang = resultSet.getInt("amount");
            int status = resultSet.getInt("status");
            String time = new TimestampConvertsTime().timestamp(resultSet.getString("createtime"));
            int phone = resultSet.getInt("purchid");
            BILLIONAIRES.add(new Billionaire(id,dingdanname,fenlei,shuliang,status,time,phone));
        }
    }

    public static BillionaireService instance() {
        return BillionaireServiceHolder.INSTANCE;
    }

    /**
     * 搜索.
     *
     * @param id
     * @param page
     */
    public void search(String id,String name,String type,String stat,long kaishitime,long jeishutime, Page<Billionaire> page) throws Exception {
        int pageSize = page.getPageSize();
        List<Billionaire> matches = new ArrayList<>();
        int totalCount = 0;
        for (Billionaire billionaire : BILLIONAIRES) {
            if (StringUtils.containsIgnoreCase(String.valueOf(billionaire.getId()), id)) {
                if (StringUtils.containsIgnoreCase(billionaire.getName(),name)) {
                    if (StringUtils.containsIgnoreCase(billionaire.getType(),type)) {
                        if (StringUtils.containsIgnoreCase(String.valueOf(billionaire.getState()),stat)) {
                            if (new TimestampConvertsTime().dateToStamp(billionaire.getNewtime())>=kaishitime) {
                                if (new TimestampConvertsTime().dateToStamp(billionaire.getNewtime())<=jeishutime) {
                                    matches.add(billionaire);
                                    totalCount++;
                                }
                            }
                        }
                    }
                }
            }
        }
        page.setTotalCount(totalCount);
        int fromIndex = page.getIndex();
        if (fromIndex > totalCount - 1) {
            page.setData(Collections.emptyList());
        } else {
            int toIndex = fromIndex + pageSize;
            if (toIndex > totalCount) {
                toIndex = totalCount;
            }
            List<Billionaire> data = matches.subList(fromIndex, toIndex);
            page.setData(data);
        }
    }

    /**
     * 供货订单删除事务处理
     *
     * @param toBeRemoved
     */
    public void remove(Billionaire toBeRemoved) throws SQLException {
        Iterator<Billionaire> iterator = BILLIONAIRES.iterator();
        Billionaire billionaire;
        while (iterator.hasNext()) {
            billionaire = iterator.next();
            if (Objects.equals(billionaire, toBeRemoved)) {
                iterator.remove();
                Mysqlservice sahnchu = new Mysqlservice();
                sahnchu.dropdingdan(toBeRemoved.getId());
            }
        }
    }
}
