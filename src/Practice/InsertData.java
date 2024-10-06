package Practice;

import java.sql.*;
public class InsertData {
    public static void main(String[] args) throws SQLException {
        Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentData","root","1234@zoro");
        String query = "insert into student values('Manajari','ME','majari12@gmail.com')";
        Statement statement = connect.createStatement();
        int rows = statement.executeUpdate(query);

    }
}
