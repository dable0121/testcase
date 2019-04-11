package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LocalDBDAO {

  private static String driver = "com.mysql.cj.jdbc.Driver";
  private static String url = "jdbc:mysql://mysql.me.glodon.com:43306/ngtrade_renewal?useSSL=true&serverTimezone=Asia/Shanghai&characterEncoding=utf-8";
  private static String user = "root";
  private static String password = "123456";

  static {
    try {
      Class.forName(driver);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(url, user, password);
  }

  public static void closeAll(Connection conn, Statement stmt, ResultSet rs) throws SQLException {
    if (rs != null) {
      rs.close();
    }
    if (stmt != null) {
      stmt.close();
    }
    if (conn != null) {
      conn.close();
    }
  }

}
