/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.*;

/**
 *
 * @author Lab Informatika
 */
public class Connector {
    public static Connection connect;
    public Statement statement;
    
    public static Connection getConnection(){
        
        if(connect == null){
            MysqlDataSource db = new MysqlDataSource();
            db.setDatabaseName("db_lomba");
            db.setUser("root");
            db.setPassword("");
            try {
                connect = db.getConnection();
                System.out.println("Connected");
            } catch (SQLException e) {
                System.out.println("Error Connection : " + e.getMessage());
            }
        }
        return connect;
    }
}
