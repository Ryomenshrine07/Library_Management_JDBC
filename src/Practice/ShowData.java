package Practice;

import java.sql.*;

public class ShowData {
    public static void main(String[] args) throws SQLException {
        Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentData","root","1234@zoro");
        Statement statement = connect.createStatement();
        ResultSet result = statement.executeQuery("SELECT * from student");
        while(result.next())
        {
            String name = result.getString("name");
            String email = result.getString("email");
            String branch = result.getString("branch");
            System.out.println(name +" "+email+" "+branch);
        }
    }
}