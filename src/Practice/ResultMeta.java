package Practice;

import java.sql.*;
public class ResultMeta {
    public static void main(String[] args) throws SQLException {
        Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentData","root","1234@zoro");
        Statement statement = connect.createStatement();
        ResultSet result = statement.executeQuery("select * from student");
        ResultSetMetaData rsmd = result.getMetaData();
        int n = rsmd.getColumnCount();
        for(int i=1;i<=n;i++)
        {
            System.out.println(rsmd.getColumnName(i) +" : "+ rsmd.getColumnTypeName(i));
        }
    }
}
