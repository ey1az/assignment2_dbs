package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.DBSCon;
import access.AuthorsAccess;
import entity.Authors;

public class AuthorsFunc extends DBSCon implements AuthorsAccess {

    @Override
    public boolean addAuthor(Authors Author) {
        try (Connection connection = connect()) {
            try (PreparedStatement stmnt = connection.prepareStatement("INSERT INTO Authors (AuthorID, AuthorFullName) VALUES (?,?)")) {
                stmnt.setInt(1, Author.getAuthorId());
                stmnt.setString(2, Author.getAuthorFullName());
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
    public List<Authors> getAllAuthors() {
        List<Authors> Authors = new ArrayList<>();
        try (Connection connection = connect()) {
            try (Statement stmnt = connection.createStatement()) {
                stmnt.execute("SELECT * FROM Authors");
                ResultSet res_set = stmnt.getResultSet();
                while (res_set.next()) {
                    int AuthorID = res_set.getInt("AuthorID");
                    String AuthorFullName = res_set.getString("AuthorFullName");
                    Authors.add(new Authors(AuthorID, AuthorFullName));
                    System.out.println("Author ID: " + AuthorID + ", Full Name: " + AuthorFullName);
                }
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.out.println("SQL Error! " + sqle.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error! " + e.getMessage());
        }

        return Authors;
    }

    @Override
    public boolean updateAuthor(Authors Author) {
        try (Connection connection = connect()) {
            try (PreparedStatement stmnt = connection.prepareStatement("UPDATE Authors SET AuthorFullName=? WHERE AuthorID=?")) {
                stmnt.setString(1, Author.getAuthorFullName());
                stmnt.setInt(2, Author.getAuthorId());
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
    public boolean deleteAuthor(int AuthorID) {
        try (Connection connection = connect()) {
            try (Statement stmnt = connection.createStatement()) {
                stmnt.execute("DELETE FROM Authors WHERE AuthorID = " + AuthorID);
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
    public Authors getAuthorByID(int author_id) {
        Authors author = null;
        try (Connection connection = connect()) {
            try (Statement stmnt = connection.createStatement()) {
                stmnt.execute("SELECT * FROM Authors WHERE AuthorID = " + author_id);
                ResultSet res_set = stmnt.getResultSet();
                while (res_set.next()) {
                    int AuthorID = res_set.getInt("AuthorID");
                    String AuthorFullName = res_set.getString("AuthorFullName");
                    author = new Authors(AuthorID, AuthorFullName);
                    System.out.println("Author ID: " + AuthorID + ", Full Name: " + AuthorFullName);
                }
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.out.println("SQL Error! " + sqle.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error! " + e.getMessage());
        }

        if (author == null) {
            System.out.println("No such AuthorID found");
        }

        return author;
    }
}
