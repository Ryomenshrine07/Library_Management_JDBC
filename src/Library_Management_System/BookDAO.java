package Library_Management_System;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private Connection connect;
    public BookDAO(Connection connect){
        this.connect = connect;
    }
    public void addBook(Book book) throws SQLException {
        String checkQuery = "SELECT COUNT(*) FROM books WHERE title=?";
        String query = "INSERT INTO books (title,author,isAvailable) VALUES(?,?,?)";
        try (PreparedStatement checkPrpt = connect.prepareStatement(checkQuery)) {
            checkPrpt.setString(1, book.getBookName());
            try (ResultSet rs = checkPrpt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    System.out.println("A book with this title already exists. Duplicate not allowed.");
                    return;
                }
            }
        }
        try(PreparedStatement prpt = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            prpt.setString(1,book.getBookName());
            prpt.setString(2,book.getAuthor());
            prpt.setBoolean(3,book.isAvailable());
            System.out.println("Book added successfully");
            try(ResultSet rs = prpt.getGeneratedKeys()) {
                if(rs.next()){
                    book.setBookID(rs.getInt(1));
                }
            }
            prpt.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public List<Book> getAllBooks() throws SQLException{
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books";
        try(PreparedStatement prpt = connect.prepareStatement(query)){
            ResultSet rs = prpt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String bookName = rs.getString("title");
                String author = rs.getString("author");
                boolean isAvailable = rs.getBoolean("isAvailable");
                Book book = new Book(id,bookName,author,isAvailable);
                books.add(book);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return books;
    }
    public void updateBook(Book book) throws SQLException {
        String query = "UPDATE books SET title = ?, author = ?, isAvailable = ? WHERE id = ?";
        try(PreparedStatement prpt = connect.prepareStatement(query)){
            prpt.setString(1,book.getBookName());
            prpt.setString(2,book.getAuthor());
            prpt.setBoolean(3,book.isAvailable());
            prpt.setInt(4,book.getBookID());
            prpt.executeUpdate();
            System.out.println("Book updated successfully");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void deleteBook(int id) throws SQLException {
        String query = "DELETE FROM books WHERE id = ?";
        try(PreparedStatement prpt = connect.prepareStatement(query)){
            prpt.setInt(1,id);
            prpt.executeUpdate();
            System.out.println("Book Deleted Successfully");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
