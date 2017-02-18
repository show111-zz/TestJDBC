
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

import java.sql.*;

/**
 * Created by lihf on 17/2/18.
 */
public class JdbcTest {
    private static final String URL = "jdbc:mysql://localhost:3306/mydata?characterEncoding=utf8&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获得数据库的连接
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            //创建SQL语句对象
            st = con.createStatement();
            //执行查询语句
            rs = st.executeQuery("SELECT * FROM dept");

            while (rs.next()) {
                System.out.println(rs.getString("deptno"));
//                System.out.println(rs.getString("sname"));
//                System.out.println(rs.getString("tie"));
//                System.out.println(rs.getString("stelephone"));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }
}
