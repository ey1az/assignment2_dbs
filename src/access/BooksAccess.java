package access;

import entity.Books;
import java.util.List;

public interface BooksAccess {
    public boolean addBook(Books Book);
    public List<Books> getAllBooks();
    public boolean updateBook(Books Book);
    public boolean deleteBook(int BookID);
    public Books getBookByID(int BookID);
}