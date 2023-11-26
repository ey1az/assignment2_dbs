package access;

import entity.Authors;
import java.util.List;

public interface AuthorsAccess {
    public boolean addAuthor(Authors Author);
    public List<Authors> getAllAuthors();
    public boolean updateAuthor(Authors Author);
    public boolean deleteAuthor(int AuthorID);
    public Authors getAuthorByID(int AuthorID);
}