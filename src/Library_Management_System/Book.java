package Library_Management_System;

public class Book {
    private int bookID;
    private String bookName;
    private String author;
    private boolean isAvailable;
    public Book(int bookID, String bookName, String author, boolean isAvailable) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.author = author;
        this.isAvailable = isAvailable;
    }
    public Book(String bookName, String author,boolean isAvailable) {
        this.bookName = bookName;
        this.author = author;
        this.isAvailable = isAvailable;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookID=" + bookID +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
