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
    public boolean addOrderedBook(OrderedBooks orderedBooks) {
        try (Connection connection = connect()) {
            connection.setAutoCommit(false);
    
            int booksLeft = getBooksLeft(connection, orderedBooks.getBook().getBookID());
            if (booksLeft < orderedBooks.getBookNum()) {
                System.out.println("Not enough books available for BookID: " + orderedBooks.getBook().getBookID());
                return false;
            }
    
            boolean orderExists;
            try (PreparedStatement stmnt = connection.prepareStatement(
                    "SELECT 1 FROM Orders WHERE OrderID = ? AND CustomerID = ?")) {
                stmnt.setInt(1, orderedBooks.getOrder().getOrderID());
                stmnt.setInt(2, orderedBooks.getOrder().getCustomer().getCustomerID());
                ResultSet resultSet = stmnt.executeQuery();
                orderExists = resultSet.next();
            }
    
            if (!orderExists) {
                try (PreparedStatement orderStatement = connection.prepareStatement(
                        "INSERT INTO Orders (OrderID, CustomerID) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                    orderStatement.setInt(1, orderedBooks.getOrder().getOrderID());
                    orderStatement.setInt(2, orderedBooks.getOrder().getCustomer().getCustomerID());
                    int affectedRows = orderStatement.executeUpdate();
    
                    if (affectedRows == 0) {
                        throw new SQLException("Creating order failed, no rows affected.");
                    }
    
                    try (ResultSet generatedKeys = orderStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            orderedBooks.getOrder().setOrderID(generatedKeys.getInt(1));
                        } else {
                            throw new SQLException("Creating order failed, no ID obtained.");
                        }
                    }
                }
            }
    
            try (PreparedStatement orderedBooksStatement = connection.prepareStatement(
                    "INSERT INTO OrderedBooks (OrderID, BookID, BookNum) VALUES (?, ?, ?)")) {
                orderedBooksStatement.setInt(1, orderedBooks.getOrder().getOrderID());
                orderedBooksStatement.setInt(2, orderedBooks.getBook().getBookID());
                orderedBooksStatement.setInt(3, orderedBooks.getBookNum());
                int affectedRows = orderedBooksStatement.executeUpdate();
    
                if (affectedRows == 0) {
                    throw new SQLException("Creating ordered book failed, no rows affected.");
                }
            }
    
            try (PreparedStatement updateBooksStatement = connection.prepareStatement(
                    "UPDATE Books SET BooksLeft = BooksLeft - ? WHERE BookID = ?")) {
                updateBooksStatement.setInt(1, orderedBooks.getBookNum());
                updateBooksStatement.setInt(2, orderedBooks.getBook().getBookID());
                int affectedRows = updateBooksStatement.executeUpdate();
    
                if (affectedRows == 0) {
                    throw new SQLException("Updating BooksLeft failed, no rows affected.");
                }
            }
    
            connection.commit();
            return true;
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
        
    private int getBooksLeft(Connection connection, int bookID) throws SQLException {
        try (PreparedStatement stmnt = connection.prepareStatement("SELECT BooksLeft FROM Books WHERE BookID = ?")) {
            stmnt.setInt(1, bookID);
            ResultSet resultSet = stmnt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("BooksLeft");
            } else {
                throw new SQLException("Book not found with ID: " + bookID);
            }
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
    public boolean updateOrderedBook(OrderedBooks orderedBooks) {
        try (Connection connection = connect()) {
            connection.setAutoCommit(false);
    
            int existingBookNum = getBookNum(connection, orderedBooks.getOrder().getOrderID(), orderedBooks.getBook().getBookID());
            int existingBookID = getBookID(connection, orderedBooks.getOrder().getOrderID(), orderedBooks.getBook().getBookID());
            
            try (PreparedStatement stmntUpdateOrderedBooks = connection.prepareStatement(
                    "UPDATE OrderedBooks SET BookNum = ? WHERE OrderID = ? AND BookID = ?")) {
                stmntUpdateOrderedBooks.setInt(1, orderedBooks.getBookNum());
                stmntUpdateOrderedBooks.setInt(2, orderedBooks.getOrder().getOrderID());
                stmntUpdateOrderedBooks.setInt(3, existingBookID);
                stmntUpdateOrderedBooks.executeUpdate();
            }
    
            try (PreparedStatement stmntUpdateBooks = connection.prepareStatement(
                    "UPDATE Books SET BooksLeft = BooksLeft - ? + ? WHERE BookID = ?")) {
                int booksLeft = getBooksLeft(connection, orderedBooks.getBook().getBookID()) + existingBookNum;
                if (booksLeft < orderedBooks.getBookNum()) {
                    System.out.println("Not enough books available for BookID: " + orderedBooks.getBook().getBookID());
                    return false;
                }
                stmntUpdateBooks.setInt(1, orderedBooks.getBookNum());
                stmntUpdateBooks.setInt(2, existingBookNum);
                stmntUpdateBooks.setInt(3, existingBookID);
                stmntUpdateBooks.executeUpdate();
            }

            connection.commit();
            return true;
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
    
    private int getBookID(Connection connection, int orderID, int bookID) throws SQLException {
        try (PreparedStatement stmnt = connection.prepareStatement(
                "SELECT BookID FROM OrderedBooks WHERE OrderID = ? AND BookID = ?")) {
            stmnt.setInt(1, orderID);
            stmnt.setInt(2, bookID);
            ResultSet resultSet = stmnt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("BookID");
            } else {
                throw new SQLException("Ordered book not found with OrderID: " + orderID + " and BookID: " + bookID);
            }
        }
    }

    @Override
    public boolean deleteOrderedBook(int OrderID, int BookID) {
        try (Connection connection = connect()) {
            connection.setAutoCommit(false);
    
            int bookNumToReturn = getBookNum(connection, OrderID, BookID);
    
            boolean hasMultipleEntries = hasMultipleEntries(connection, OrderID);
    
            if (hasMultipleEntries) {
                try (PreparedStatement stmnt = connection.prepareStatement(
                        "DELETE FROM OrderedBooks WHERE OrderID = ? AND BookID = ?")) {
                    stmnt.setInt(1, OrderID);
                    stmnt.setInt(2, BookID);
                    stmnt.executeUpdate();
                }
            } else {
                try (PreparedStatement stmntOrderedBooks = connection.prepareStatement(
                        "DELETE FROM OrderedBooks WHERE OrderID = ? AND BookID = ?")) {
                    stmntOrderedBooks.setInt(1, OrderID);
                    stmntOrderedBooks.setInt(2, BookID);
                    stmntOrderedBooks.executeUpdate();
                }
    
                try (PreparedStatement stmntOrders = connection.prepareStatement(
                        "DELETE FROM Orders WHERE OrderID = ?")) {
                    stmntOrders.setInt(1, OrderID);
                    stmntOrders.executeUpdate();
                }
            }
    
            try (PreparedStatement updateBooksStatement = connection.prepareStatement(
                    "UPDATE Books SET BooksLeft = BooksLeft + ? WHERE BookID = ?")) {
                updateBooksStatement.setInt(1, bookNumToReturn);
                updateBooksStatement.setInt(2, BookID);
                updateBooksStatement.executeUpdate();
            }
    
            connection.commit();
            return true;
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
    
    private boolean hasMultipleEntries(Connection connection, int OrderID) throws SQLException {
        try (PreparedStatement stmnt = connection.prepareStatement(
                "SELECT COUNT(*) AS entryCount FROM OrderedBooks WHERE OrderID = ?")) {
            stmnt.setInt(1, OrderID);
            ResultSet resultSet = stmnt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("entryCount") > 1;
            } else {
                throw new SQLException("Error determining the number of entries for OrderID: " + OrderID );
            }
        }
    }    
    
    private int getBookNum(Connection connection, int OrderID, int BookID) throws SQLException {
        try (PreparedStatement stmnt = connection.prepareStatement(
                "SELECT BookNum FROM OrderedBooks WHERE OrderID = ? AND BookID = ?")) {
            stmnt.setInt(1, OrderID);
            stmnt.setInt(2, BookID);
            ResultSet resultSet = stmnt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("BookNum");
            } else {
                throw new SQLException("Ordered book not found with OrderID: " + OrderID + " and BookID: " + BookID);
            }
        }
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
