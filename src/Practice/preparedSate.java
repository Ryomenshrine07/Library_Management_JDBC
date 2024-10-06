package Practice;

import java.sql.*;
public class preparedSate {
    public static void main(String[] args) throws SQLException {
        Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentData","root","1234@zoro");
        PreparedStatement pStatement = connect.prepareStatement("insert into student values(?,?,?)");
        pStatement.setString(1,"Priyanka");
        pStatement.setString(2,"CSBS");
        pStatement.setString(3,"pthapa@gamail.com");
        pStatement.executeUpdate();
    }

}
