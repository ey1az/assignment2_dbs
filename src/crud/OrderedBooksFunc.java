package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.DBSCon;
import access.OrderedBooksAccess;
import entity.OrderedBooks;
import entity.Orders;
import entity.Books;

public class OrderedBooksFunc extends DBSCon implements OrderedBooksAccess {

    @Override
    public boolean addOrderedBook(OrderedBooks OrderedBooks) {
        try (Connection connection = connect()) {
            try (PreparedStatement stmnt = connection.prepareStatement("INSERT INTO OrderedBooks (OrderID, BookID, BookNum) VALUES (?, ?, ?)")) {
                stmnt.setInt(1, OrderedBooks.getOrder().getOrderID());
                stmnt.setInt(2, OrderedBooks.getBook().getBookID());
                stmnt.setInt(3, OrderedBooks.getBookNum());
                return stmnt.execute();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.out.println("SQL Error! " + sqle.getMessage());
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error! " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<OrderedBooks> getAllOrderedBooks() {
        List<OrderedBooks> OrderedBooksList = new ArrayList<>();
        try (Connection connection = connect()) {
            try (Statement stmnt = connection.createStatement()) {
                stmnt.execute("SELECT * FROM OrderedBooks");
                ResultSet res_set = stmnt.getResultSet();
                while (res_set.next()) {
                    int OrderID = res_set.getInt("OrderID");
                    int BookID = res_set.getInt("BookID");
                    int BookNum = res_set.getInt("BookNum");

                    Orders Order = new Orders();
                    Order.setOrderID(OrderID);

                    Books Book = new Books();
                    Book.setBookID(BookID);

                    OrderedBooks OrderedBook = new OrderedBooks(Order, Book, BookNum);
                    OrderedBook.setBookNum(BookNum);
                    OrderedBooksList.add(OrderedBook);
                    System.out.println("Order ID: " + OrderID + ", Book ID: " + BookID + ", BookNum: " + BookNum);
                }
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.out.println("SQL Error! " + sqle.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error! " + e.getMessage());
        }

        return OrderedBooksList;
    }

    @Override
    public boolean updateOrderedBook(OrderedBooks OrderedBooks) {
        try (Connection connection = connect()) {
            try (PreparedStatement stmnt = connection.prepareStatement("UPDATE OrderedBooks SET BookID=?, BookNum=? WHERE OrderID=?")) {
                stmnt.setInt(1, OrderedBooks.getBook().getBookID());
                stmnt.setInt(2, OrderedBooks.getBookNum());
                stmnt.setInt(3, OrderedBooks.getOrder().getOrderID());
                return stmnt.execute();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.out.println("SQL Error! " + sqle.getMessage());
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error! " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteOrderedBook(int OrderID, int BookID) {
        try (Connection connection = connect()) {
            try (Statement stmnt = connection.createStatement()) {
                stmnt.execute("DELETE FROM OrderedBooks WHERE OrderID = '" + OrderID + "' AND BookID = " + BookID);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.out.println("SQL Error! " + sqle.getMessage());
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error! " + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public OrderedBooks getOrderedBookAndOrderByID(int OrderID, int BookID) {
        OrderedBooks OrderedBook = null;
        try (Connection connection = connect()) {
            try (Statement stmnt = connection.createStatement()) {
                stmnt.execute("SELECT * FROM OrderedBooks WHERE OrderID = '" + OrderID + "' AND BookID = " + BookID);
                ResultSet res_set = stmnt.getResultSet();
                while (res_set.next()) {
                    int RetrievedOrderID = res_set.getInt("OrderID");
                    int RetrievedBookID = res_set.getInt("BookID");
                    int BookNum = res_set.getInt("BookNum");

                    Orders Order = new Orders();
                    Order.setOrderID(RetrievedOrderID);

                    Books Book = new Books();
                    Book.setBookID(RetrievedBookID);

                    OrderedBook = new OrderedBooks(Order, Book, BookNum);
                    OrderedBook.setBookNum(BookNum);
                    System.out.println("Order ID: " + RetrievedOrderID + ", Book ID: " + RetrievedBookID + ", BookNum: " + BookNum);
                }
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.out.println("SQL Error! " + sqle.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error! " + e.getMessage());
        }

        if (OrderedBook == null) {
            System.out.println("No such OrderID or BookID found.");
        }

        return OrderedBook;
    }
}
