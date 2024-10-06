package Library_Management_System;

import java.util.Date;

public class Transaction {
    private int transactionID;
    private int memberID;
    private int bookID;
    private Date issueDate;
    private Date returnDate;

    Transaction(int memberID,int bookID, Date issueDate, Date returnDate) {
        this.memberID = memberID;
        this.bookID = bookID;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }
    Transaction(int transactionID, int memberID,int bookID, Date issueDate, Date returnDate) {
        this.transactionID = transactionID;
        this.memberID = memberID;
        this.bookID = bookID;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }


    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public java.sql.Date getIssueDate() {
        return new java.sql.Date(issueDate.getTime());
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionID=" + transactionID +
                ", memberID=" + memberID +
                ", bookID=" + bookID +
                ", issueDate=" + issueDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
