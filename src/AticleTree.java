import java.sql.*;

/**
 * Created by lihf on 17/2/18.
 */
public class AticleTree {
    private static final String URL = "jdbc:mysql://localhost:3306/bbs?characterEncoding=utf8&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "root";


    public static void main(String[] args) {
        new AticleTree().show();
    }

    public void show() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获得数据库的连接
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM article WHERE pid = 0");
            while (rs.next()) {
                System.out.println("cont");
                tree(conn, rs.getInt("id"), 1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (conn != null) {
                    conn.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void tree(Connection conn, int id, int level) {
        Statement stmt = null;
        ResultSet rs = null;
        StringBuffer strPre = new StringBuffer("");
        for (int i = 0; i < level; i++) {
            strPre.append("     ");
        }

        try {
            stmt = conn.createStatement();
            String sql = "select * from article where pid =" + id;
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println(strPre + rs.getString("cont"));
                if (rs.getInt("isleaf") != 0) {
                    tree(conn, rs.getInt("id"), level + 1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
