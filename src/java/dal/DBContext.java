package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBContext {

    protected Connection connection;

    public DBContext() {
        try {
            String user = "sa"; // Thay đổi tên người dùng nếu cần
            String pass = "123"; // Thay đổi mật khẩu nếu cần
            String url = "jdbc:sqlserver://localhost\\KTEAM:1433;databaseName=Assignment";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Thêm phương thức để kiểm tra kết nối
    public boolean testConnection() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    public static void main(String[] args) {
        DBContext dbContext = new DBContext();
        boolean isConnected = dbContext.testConnection();

        if (isConnected) {
            System.out.println("Kết nối đến cơ sở dữ liệu thành công!");
        } else {
            System.out.println("Không thể kết nối đến cơ sở dữ liệu!");
        }
    }
}


