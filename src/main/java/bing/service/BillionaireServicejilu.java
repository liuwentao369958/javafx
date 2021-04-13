package bing.service;

import bing.common.Page;
import bing.jdbc.Mysqlservice;
import bing.model.Billionaire;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * Billionaire Query Service
 *供货订单事务处理类
 * @author: IceWee
 * @date: 2019.1.2
 */
public final class BillionaireServicejilu {
    public Connection conn;
    public Statement  stmt;

    private static class BillionaireServiceHolder  {
        private static BillionaireServicejilu INSTANCE = null;

        static {
            try {
                INSTANCE = new BillionaireServicejilu();
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
    public BillionaireServicejilu()throws Exception {
       /*根据用户名查找改用户发布的订单
        Controller controller = new Controller();
       List<String> sa = controller.list;
        for (String user : sa){
            username = user;
        }*/
        Mysqlservice mysqlservice = new Mysqlservice();
        ResultSet resultSet = mysqlservice.selectdingdanJL();
        while (resultSet.next()){
            String id = resultSet.getString("orderno");
            String dingdanname = resultSet.getString("operatid");
            String fenlei =resultSet.getString("catid");
            int shuliang = resultSet.getInt("realamount");
            int status = resultSet.getInt("status");
            String time =new TimestampConvertsTime().timestamp(resultSet.getString("createtime"));
            int phone = resultSet.getInt("realprice");
            BILLIONAIRES.add(new Billionaire(id,dingdanname,fenlei,shuliang,status,time,phone));
        }
    }

    public static BillionaireServicejilu instance() {
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
                sahnchu.dropdingdanJL(toBeRemoved.getId());
            }
        }
    }
}
