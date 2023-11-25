package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import entity.Authors;
import entity.Books;
import entity.Customers;
import entity.OrderedBooks;
import entity.Orders;
import entity.BooksAuthors;

public abstract class DBSCon {
    public static Connection connect() {
        String URL = "jdbc:postgresql://localhost:5432/as2_test";
        String user = "postgres";
        String password = "admin1234";

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(URL, user, password);
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return connection;
    }

    public static void main(String[] args) {
 
    }
}

