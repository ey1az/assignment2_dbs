package entity;

public class BooksAuthors {
    private Authors AuthorID;
    private Books BookID;

    public BooksAuthors() {
    }

    public BooksAuthors(Authors AuthorID, Books BookID) {
        this.AuthorID = AuthorID;
        this.BookID = BookID;
    }

    public Authors getBookAuthor() {
        return AuthorID;
    }

    public void setBookAuthor(Authors AuthorID) {
        this.AuthorID= AuthorID;
    }

    public Books getAuthorBook() {
        return BookID;
    }

    public void setAuthorBook(Books BookID) {
        this.BookID = BookID;
    }

    @Override
    public String toString() {
        return "BooksAuthors{" +
                "AuthorID=" + AuthorID +
                ", BookID=" + BookID +
                '}';
    }
}
