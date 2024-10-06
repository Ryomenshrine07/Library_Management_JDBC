package Library_Management_System;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {
    Connection connect;
    TransactionDAO(Connection connect){
        this.connect = connect;
    }
    public void issueBook(Transaction transaction) throws SQLException {
        String query = "INSERT INTO transactions (member_id, book_Id, issueDate, returnDate) VALUES(?,?,?,?)";
        try(PreparedStatement prpt = connect.prepareStatement(query)){
            prpt.setInt(1,transaction.getMemberID());
            prpt.setInt(2,transaction.getBookID());
            prpt.setDate(3,transaction.getIssueDate());
            prpt.setNull(4, Types.DATE);
            prpt.executeUpdate();
        }
        String updateQuery = "UPDATE books SET isAvailable = ? WHERE id = ?";
        try(PreparedStatement prpt = connect.prepareStatement(updateQuery)){
            prpt.setBoolean(1,false);
            prpt.setInt(2,transaction.getBookID());
            prpt.executeUpdate();
            System.out.println("Book Issued to ");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void returnBook(int transactionId, Date returnDate) throws SQLException {
        String query = "UPDATE transactions SET returnDate = ? WHERE transaction_id = ?";
        try(PreparedStatement prpt = connect.prepareStatement(query)){
            prpt.setDate(1,returnDate);
            prpt.setInt(2,transactionId);
            prpt.executeUpdate();
        }
        String updateQuery = "UPDATE books SET isAvailable = ? WHERE id = ?";
        try(PreparedStatement prpt = connect.prepareStatement(updateQuery)){
            prpt.setBoolean(1,true);
            prpt.setInt(2,getBookIdFromTransactionId(transactionId));
            prpt.executeUpdate();
            System.out.println("Book Returned");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public List<Transaction> getAllTransactions() throws SQLException {
        String query = "SELECT * FROM transactions";
        List<Transaction> transactions = new ArrayList<>();
        try(PreparedStatement prpt = connect.prepareStatement(query)){
            ResultSet rs = prpt.executeQuery();
            while(rs.next()){
                int transactionId = rs.getInt(1);
                int memberID = rs.getInt(2);
                int bookID = rs.getInt(3);
                Date issueDate = rs.getDate(4);
                Date returnDate = rs.getDate(5);
                Transaction transaction = new Transaction(transactionId,memberID,bookID,issueDate,returnDate);
                transactions.add(transaction);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return transactions;
    }
    public int getBookIdFromTransactionId(int transactionId) throws SQLException {
        String query = "SELECT book_id FROM transactions WHERE transaction_id = ?";
        try(PreparedStatement prpt = connect.prepareStatement(query)){
            prpt.setInt(1,transactionId);
            ResultSet rs = prpt.executeQuery();
            if(rs.next()){
                return rs.getInt("book_id");
            }
        }
        return  -1;
    }
}
