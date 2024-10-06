package Library_Management_System;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void adminMethods(BookDAO bookDAO, MemberDAO memberDAO,TransactionDAO transactionDAO) {
        while(true){
            System.out.println("1. Add Book");
            System.out.println("2. Delete Book");
            System.out.println("3. Edit Book");
            System.out.println("4. Show All Books");
            System.out.println("5. Delete Member");
            System.out.println("6. Edit Member");
            System.out.println("7. Show All Members");
            System.out.println("8. Show All Transactions");
            System.out.println("9. Exit");
            System.out.println("Enter your choice: ");
            int ch = sc.nextInt();
            try {
                switch (ch){
                    case 1 -> {
                        System.out.println("Enter Book Title :");
                        String title = sc.next();
                        System.out.println("Enter Book Author :");
                        String author = sc.next();
                        Book book = new Book(title, author, true);
                        bookDAO.addBook(book);
                    }
                    case 2 -> {
                        System.out.println("Enter Book ID :");
                        int id = sc.nextInt();
                        bookDAO.deleteBook(id);
                    }
                    case 3 -> {
                        System.out.println("Enter Book id :");
                        int id = sc.nextInt();
                        System.out.println("Enter New Book Title :");
                        String title = sc.next();
                        System.out.println("Enter New Book Author :");
                        String author = sc.next();
                        Book book = new Book(id, title, author,true);
                        bookDAO.updateBook(book);
                    }
                    case 4 -> {
                        List<Book> books = bookDAO.getAllBooks();
                        for(Book book : books){
                            System.out.println(book);
                        }
                    }
                    case 5 -> {
                        System.out.println("Enter Member ID :");
                        int id = sc.nextInt();
                        memberDAO.removeMember(id);
                    }
                    case 6 -> {
                        System.out.println("Enter Member ID :");
                        int id = sc.nextInt();
                        System.out.println("Enter New Member Name :");
                        String name = sc.next();
                        System.out.println("Enter New Member Phone :");
                        String phone = sc.next();
                        System.out.println("Enter New Member Ship (Premium / Regular) :");
                        String memberShip = sc.next();
                        Member member = new Member(id,name, phone, memberShip);
                        memberDAO.updateMember(member);
                    }
                    case 7 -> {
                        List<Member> members = memberDAO.getAllMember();
                        for (Member member : members) {
                            System.out.println(member);
                        }
                    }
                    case 8 -> {
                        List<Transaction> transactions = transactionDAO.getAllTransactions();
                        for (Transaction transaction : transactions) {
                            System.out.println(transaction);
                        }
                    }
                    case 9 -> {
                        break;
                    }
                }
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    public static void studentMethods(BookDAO bookDAO, TransactionDAO transactionDAO,MemberDAO memberDAO) {
        while(true){
            System.out.println("1. Show All Books");
            System.out.println("2. Issue Book");
            System.out.println("3. Return Book");
            System.out.println("Enter your choice");
            int ch = sc.nextInt();
            try{
                switch(ch){
                    case 1 -> {
                        List<Book> books = bookDAO.getAllBooks();
                        for(Book book : books){
                            System.out.println(book);
                        }
                    }
                    case 2 -> {
                        System.out.println("Enter Member name :");
                        String name = sc.next();
                        System.out.println("Enter Member Phone :");
                        String phone = sc.next();
                        System.out.println("Enter Member MemberShip (Premium / Regular) :");
                        String memberShip = sc.next();
                        Member member = new Member(name, phone, memberShip);
                        memberDAO.addMember(member);
                        System.out.println("Enter BookID :");
                        int bookID = sc.nextInt();
                        Transaction transaction = new Transaction(member.getMember_id(),bookID,new java.util.Date(),null);
                        transactionDAO.issueBook(transaction);
                    }
                    case 3 -> {
                        System.out.println("Enter Transaction ID To Return Book :");
                        int transactionId = sc.nextInt();
                        java.util.Date utilDate = new java.util.Date();
                        java.sql.Date sqlReturnDate = new java.sql.Date(utilDate.getTime());
                        transactionDAO.returnBook(transactionId, sqlReturnDate);
                    }
                    default -> {
                        break;
                    }
                }
            }
            catch (SQLException e){
                e.printStackTrace();
            }

        }
    }
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        try(Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","1234@zoro")) {
            BookDAO bookDAO = new BookDAO(connect);
            MemberDAO memberDAO = new MemberDAO(connect);
            TransactionDAO transactionDAO = new TransactionDAO(connect);
            while(true) {
                System.out.println("1. Login as Admin");
                System.out.println("2. Login as Student");
                System.out.println("Enter your choice: ");
                int ch = sc.nextInt();
                switch(ch) {
                    case 1: adminMethods(bookDAO,memberDAO,transactionDAO);
                    break;
                    case 2: studentMethods(bookDAO,transactionDAO,memberDAO);
                    break;
                    default: break;
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
