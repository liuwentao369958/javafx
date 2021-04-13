package bing.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Mysql {
    public Connection conn;
    public Statement stmt;
    public void jdbc() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dzcjk?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", "root", "123456");
            stmt = conn.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
