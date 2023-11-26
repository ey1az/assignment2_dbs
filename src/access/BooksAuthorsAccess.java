package access;

import entity.BooksAuthors;
import java.util.List;

public interface BooksAuthorsAccess {
    public boolean addBookAuthor(BooksAuthors BooksAuthors);
    public List<BooksAuthors> getAllBooksAuthors();
    public boolean updateBookAuthor(BooksAuthors BooksAuthors);
    public boolean deleteBookAuthor(int AuthorID, int BookID);
    public BooksAuthors getBookAndAuthorByID(int AuthorID,int BookID);
}