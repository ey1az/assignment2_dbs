package crud;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.DBSCon;
import entity.Authors;
import entity.Books;
import entity.Customers;
import entity.Orders;
import entity.OrderedBooks;
import entity.TotalBooksInfo;

public class TotalBooksInfoFunc extends DBSCon {

    public List<TotalBooksInfo> getTotalBooksInfo() {
        List<TotalBooksInfo> totalBooksInfoList = new ArrayList<>();
        try (Connection connection = connect()) {
            String query = "SELECT B.BookID, B.Title, B.Edition, B.Publisher, B.Pages, B.Year, B.BooksLeft, B.Price, " +
                    "A.AuthorID, A.AuthorFullName, " +
                    "O.OrderID, O.CustomerID, O.FullPrice, " +
                    "OB.BookNum " +
                    "FROM Books B " +
                    "JOIN BooksAuthors BA ON B.BookID = BA.BookID " +
                    "JOIN Authors A ON BA.AuthorID = A.AuthorID " +
                    "LEFT JOIN OrderedBooks OB ON B.BookID = OB.BookID " +
                    "LEFT JOIN Orders O ON OB.OrderID = O.OrderID";

            try (Statement stmnt = connection.createStatement()) {
                stmnt.execute(query);
                ResultSet res_set = stmnt.getResultSet();

                while (res_set.next()) {
                    int bookID = res_set.getInt("BookID");
                    String title = res_set.getString("Title");
                    int edition = res_set.getInt("Edition");
                    String publisher = res_set.getString("Publisher");
                    int pages = res_set.getInt("Pages");
                    int year = res_set.getInt("Year");
                    int booksLeft = res_set.getInt("BooksLeft");
                    double price = res_set.getDouble("Price");

                    int authorID = res_set.getInt("AuthorID");
                    String authorFullName = res_set.getString("AuthorFullName");

                    int orderID = res_set.getInt("OrderID");
                    int customerID = res_set.getInt("CustomerID");
                    double fullPrice = res_set.getDouble("FullPrice");

                    int bookNum = res_set.getInt("BookNum");

                    Books book = new Books(bookID, title, edition, publisher, pages, year, booksLeft, price);
                    Authors author = new Authors(authorID, authorFullName);
                    Customers customer = new Customers();
                    customer.setCustomerID(customerID);
                    Orders order = new Orders(orderID, customer, fullPrice);
                    OrderedBooks orderedBooks = new OrderedBooks(order, book, bookNum);

                    TotalBooksInfo totalBooksInfo = new TotalBooksInfo(book, author, order, orderedBooks);
                    totalBooksInfoList.add(totalBooksInfo);
                }

                for (TotalBooksInfo booksInfo : totalBooksInfoList) {
                    System.out.println(booksInfo.toString());
                }
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.out.println("SQL Error! " + sqle.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error! " + e.getMessage());
        }

        return totalBooksInfoList;
    }
}
