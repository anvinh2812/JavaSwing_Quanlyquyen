package View;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectSQL {
    public static Connection connectDB(){
        Connection conn = null;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;database=quanlyquyen;user=sa;password=2812;encrypt=true;trustServerCertificate=true";
            conn = DriverManager.getConnection(url);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConnectSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    public static void main(String[] args) {
        try {
            connectDB();
            System.out.println("Đã kết nối đến cơ sở dữ liệu");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
