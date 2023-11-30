package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.DBSCon;
import access.BooksAccess;
import entity.Books;
public class BooksFunc extends DBSCon implements BooksAccess {

    @Override
    public boolean addBook(Books Book) {
        try (Connection connection = connect()) {
            try (PreparedStatement stmnt = connection.prepareStatement("INSERT INTO Books (BookID, Title, Edition, Publisher, Pages, Year, BooksLeft, Price) VALUES (?,?,?,?,?,?,?,?)")) {
                stmnt.setInt(1, Book.getBookID());
                stmnt.setString(2, Book.getTitle());
                stmnt.setInt(3, Book.getEdition());
                stmnt.setString(4, Book.getPublisher());
                stmnt.setInt(5, Book.getPages());
                stmnt.setInt(6, Book.getYear());
                stmnt.setInt(7, Book.getBooksLeft());
                stmnt.setDouble(8, Book.getPrice());
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
    public List<Books> getAllBooks() {
        List<Books> BooksList = new ArrayList<>();
        try (Connection connection = connect()) {
            try (Statement stmnt = connection.createStatement()) {
                stmnt.execute("SELECT * FROM Books");
                ResultSet res_set = stmnt.getResultSet();
                while (res_set.next()) {
                    int BookID = res_set.getInt("BookID");
                    String Title = res_set.getString("Title");
                    int Edition = res_set.getInt("Edition");
                    String Publisher = res_set.getString("Publisher");
                    int Pages = res_set.getInt("Pages");
                    int Year = res_set.getInt("Year");
                    int BooksLeft = res_set.getInt("BooksLeft");
                    double Price = res_set.getDouble("Price");

                    Books Book = new Books(BookID, Title, Edition, Publisher, Pages, Year, BooksLeft, Price);
                    BooksList.add(Book);
                    System.out.println("Book ID: " + BookID + ", Title: " + Title +
                            ", Edition: " + Edition + ", Publisher: " + Publisher + ", Pages: " + Pages +
                            ", Year: " + Year + ", Books Left: " + BooksLeft + ", Price: " + Price);
                }
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.out.println("SQL Error! " + sqle.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error! " + e.getMessage());
        }

        return BooksList;
    }

    @Override
    public boolean updateBook(Books Book) {
        try (Connection connection = connect()) {
            Books existingBook = getBookByID(Book.getBookID());

            if (existingBook == null) {
                return false;
            }

            try (PreparedStatement stmnt = connection.prepareStatement("UPDATE Books SET Title=?, Edition=?, Publisher=?, Pages=?, Year=?, BooksLeft=?, Price=? WHERE BookID=?")) {
                stmnt.setString(1, Book.getTitle());
                stmnt.setInt(2, Book.getEdition());
                stmnt.setString(3, Book.getPublisher());
                stmnt.setInt(4, Book.getPages());
                stmnt.setInt(5, Book.getYear());
                stmnt.setInt(6, Book.getBooksLeft());
                stmnt.setDouble(7, Book.getPrice());
                stmnt.setInt(8, Book.getBookID());
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
    public boolean deleteBook(int BookID) {
        try (Connection connection = connect()) {
            Books existingBook = getBookByID(BookID);

            if (existingBook == null) {
                return false;
            }

            try (Statement stmnt = connection.createStatement()) {
                stmnt.execute("DELETE FROM Books WHERE BookID = " + BookID);
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
    public Books getBookByID(int BookID) {
        Books Book = null;
        try (Connection connection = connect()) {
            try (Statement stmnt = connection.createStatement()) {
                stmnt.execute("SELECT * FROM Books WHERE BookID = " + BookID);
                ResultSet res_set = stmnt.getResultSet();
                while (res_set.next()) {
                    int RetrievedBookID = res_set.getInt("BookID");
                    String Title = res_set.getString("Title");
                    int Edition = res_set.getInt("Edition");
                    String Publisher = res_set.getString("Publisher");
                    int Pages = res_set.getInt("Pages");
                    int Year = res_set.getInt("Year");
                    int BooksLeft = res_set.getInt("BooksLeft");
                    double Price = res_set.getDouble("Price");

                    Book = new Books(RetrievedBookID, Title, Edition, Publisher, Pages, Year, BooksLeft, Price);
                    System.out.println("Book ID: " + BookID + ", Title: " + Title +
                            ", Edition: " + Edition + ", Publisher: " + Publisher + ", Pages: " + Pages +
                            ", Year: " + Year + ", Books Left: " + BooksLeft + ", Price: " + Price);
                }
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.out.println("SQL Error! " + sqle.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error! " + e.getMessage());
        }

        if (Book == null) {
            System.out.println("No such BookID found.");
        }     

        return Book;
    }    
}
