package entity;

public class Authors {
    private int AuthorID;
    private String AuthorFullName;

    public Authors() {
    }

    public Authors(int AuthorID, String AuthorFullName) {
        this.AuthorID = AuthorID;
        this.AuthorFullName = AuthorFullName;
    }

    public int getAuthorId() {
        return AuthorID;
    }

    public void setAuthorId(int AuthorID) {
        this.AuthorID = AuthorID;
    }

    public String getAuthorFullName() {
        return AuthorFullName;
    }

    public void setAuthorFullName(String AuthorFullName) {
        this.AuthorFullName = AuthorFullName;
    }

    @Override
    public String toString() {
        return "Author{" +
                "AuthorID=" + AuthorID +
                ", AuthorFullName=" + AuthorFullName +
                '}';
    }
}