package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.DBSCon;
import access.BooksAuthorsAccess;
import entity.Authors;
import entity.Books;
import entity.BooksAuthors;

public class BooksAuthorsFunc extends DBSCon implements BooksAuthorsAccess {

    @Override
    public boolean addBookAuthor(BooksAuthors BooksAuthors) {
        try (Connection connection = connect()) {
            try (PreparedStatement stmnt = connection.prepareStatement("INSERT INTO BooksAuthors (AuthorID, BookID) VALUES (?, ?)")) {
                stmnt.setInt(1, BooksAuthors.getBookAuthor().getAuthorId());
                stmnt.setInt(2, BooksAuthors.getAuthorBook().getBookID());
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
    public List<BooksAuthors> getAllBooksAuthors() {
        List<BooksAuthors> BooksAuthorsList = new ArrayList<>();
        try (Connection connection = connect()) {
            try (Statement stmnt = connection.createStatement()) {
                stmnt.execute("SELECT * FROM BooksAuthors");
                ResultSet res_set = stmnt.getResultSet();
                while (res_set.next()) {
                    int AuthorID = res_set.getInt("AuthorID");
                    int BookID = res_set.getInt("BookID");

                    Authors Author = new Authors();
                    Author.setAuthorId(AuthorID);

                    Books Book = new Books();
                    Book.setBookID(BookID);

                    BooksAuthors BooksAuthor = new BooksAuthors(Author, Book);
                    BooksAuthorsList.add(BooksAuthor);
                    System.out.println("Author ID: " + AuthorID + ", Book ID: " + BookID);
                }
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.out.println("SQL Error! " + sqle.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error! " + e.getMessage());
        }

        return BooksAuthorsList;
    }

    @Override
    public boolean updateBookAuthor(BooksAuthors BooksAuthors) {
        try (Connection connection = connect()) {
            try (PreparedStatement stmnt = connection.prepareStatement("UPDATE BooksAuthors SET BookID=? WHERE AuthorID=?")) {
                stmnt.setInt(1, BooksAuthors.getAuthorBook().getBookID());
                stmnt.setInt(2, BooksAuthors.getBookAuthor().getAuthorId());
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
    public boolean deleteBookAuthor(int AuthorID, int BookID) {
        try (Connection connection = connect()) {
            try (Statement stmnt = connection.createStatement()) {
                stmnt.execute("DELETE FROM BooksAuthors WHERE AuthorID = '" + AuthorID + "' AND BookID = " + BookID);
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
    public BooksAuthors getBookAndAuthorByID(int AuthorID, int BookID) {
        BooksAuthors BooksAuthor = null;
        try (Connection connection = connect()) {
            try (Statement stmnt = connection.createStatement()) {
                stmnt.execute("SELECT * FROM BooksAuthors WHERE AuthorID = '" + AuthorID + "' AND BookID = " + BookID);
                ResultSet res_set = stmnt.getResultSet();
                while (res_set.next()) {
                    int RetrievedAuthorID = res_set.getInt("AuthorID");
                    int RetrievedBookID = res_set.getInt("BookID");

                    Authors Author = new Authors();
                    Author.setAuthorId(RetrievedAuthorID);

                    Books Book = new Books();
                    Book.setBookID(RetrievedBookID);

                    BooksAuthor = new BooksAuthors(Author, Book);
                    System.out.println("Author ID: " + RetrievedAuthorID + ", Book ID: " + RetrievedBookID);
                }
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.out.println("SQL Error! " + sqle.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error! " + e.getMessage());
        }

        if (BooksAuthor == null) {
            System.out.println("No such AuthorID or BookID found.");
        }       

        return BooksAuthor;
    }
}
