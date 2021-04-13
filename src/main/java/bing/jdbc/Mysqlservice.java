package bing.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Mysqlservice {
    Mysql mysql = new Mysql();

    //登陆查询用户信息
    public ResultSet selectuser() throws SQLException {
        mysql.jdbc();
        String sql;
        sql = "SELECT * from user";
        ResultSet rs = mysql.stmt.executeQuery(sql);
        return rs;
    }
    public Boolean selectusername(String name) throws SQLException {
        mysql.jdbc();
        String sql;
        sql = "SELECT * from user where username="+name;
        ResultSet rs = mysql.stmt.executeQuery(sql);
        if (rs.next()){
            return true;
        }
        return false;
    }
    public String selectuserpawd(String name) throws SQLException {
        mysql.jdbc();
        String sql;
        sql = "SELECT password from user where username="+name;
        ResultSet rs = mysql.stmt.executeQuery(sql);
        if (rs.next()){
            return rs.getString("password");
        }
        return null;
    }
    //供货订单
    public ResultSet selectdingdan() throws SQLException {
        mysql.jdbc();
        String sql = "SELECT * FROM fa_supply_gong";
        ResultSet rs = mysql.stmt.executeQuery(sql);
        return rs;
    }

    //根据tableview的下标查询订单
    public ResultSet selectindexdingdan(String index) throws SQLException {
        mysql.jdbc();
        String sql = "select * from fa_supply_gong where orderno="+index;
        ResultSet rs = mysql.stmt.executeQuery(sql);
        return rs;
    }
    /*
    //删除订单
    供货订单删除
    */
    public boolean dropdingdan(String a) throws SQLException {

        mysql.jdbc();
        String sql="delete from fa_supply_gong where orderno="+a;
        int b = mysql.stmt.executeUpdate(sql);
        if(b!=0){
            return true;
        }
        return false;
    }

    //采购订单 页面初始化，查询改用户的全部订单
    public ResultSet selectdingdanCG() throws SQLException {
        mysql.jdbc();
        String sql = "SELECT * FROM fa_supply_cai";
        ResultSet rs = mysql.stmt.executeQuery(sql);
        return rs;
    }

    //采购订单 根据tableview的下标查询订单
    public ResultSet selectindexdingdanCG(String index) throws SQLException {
        mysql.jdbc();
        String sql = "select * from fa_supply_cai where orderno="+index;
        ResultSet rs = mysql.stmt.executeQuery(sql);
        return rs;
    }
    /*
    //删除订单
    采购订单删除
    */
    public boolean dropdingdanCG(String a) throws SQLException {
        mysql.jdbc();
        String sql="delete from fa_supply_cai where orderno="+a;
        int b = mysql.stmt.executeUpdate(sql);
        if(b!=0){
            return true;
        }
        return false;
    }

    public boolean updatacgdingdan(String Orderno,int status){
        mysql.jdbc();
        String sql = "UPDATE fa_supply_cai SET STATUS="+status+" WHERE orderno="+Orderno ;
        try{
            int a = mysql.stmt.executeUpdate(sql);
            return true;
        }catch (Exception e){
            return false;
        }
    }


    //查询订单记录
    public ResultSet selectdingdanJL() throws SQLException {
        mysql.jdbc();
        String sql = "SELECT * FROM fa_purchase_record";
        ResultSet rs = mysql.stmt.executeQuery(sql);
        return rs;
    }

    //订单记录 根据tableview的下标查询订单
    public ResultSet selectindexdingdanJL(String index) throws SQLException {
        mysql.jdbc();
        String sql = "select * from fa_purchase_record where orderno="+index;
        ResultSet rs = mysql.stmt.executeQuery(sql);
        return rs;
    }
    /*
    //删除订单
    删除订单记录删除
    */
    public boolean dropdingdanJL(String a) throws SQLException {

        mysql.jdbc();
        String sql="delete from fa_purchase_record where orderno="+a;
        int b = mysql.stmt.executeUpdate(sql);
        if(b!=0){
            return true;
        }
        return false;
    }
}
