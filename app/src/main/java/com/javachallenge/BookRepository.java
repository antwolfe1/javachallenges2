package com.javachallenge;


import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BookRepository {

    private final Connection connection;

    public static BookRepository getInstance() {
        return new BookRepository();
    }

    private BookRepository() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bookdb", "user", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Book> getAllBooks() {
        Book book = null;
        Statement statement;
        ResultSet resultSet;
        List<Book> bookList = new ArrayList<>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM books");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                int pages = resultSet.getInt("pages");
                float price = resultSet.getFloat("price");
                book = new Book(id, title, author, pages, price);
                bookList.add(book);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bookList;
    }

}
