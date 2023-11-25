package entity;

public class Books {
    private int BookID;
    private String Title;
    private int Edition;
    private String Publisher;
    private int Pages;
    private int Year;
    private int BooksLeft;
    private double Price;

    public Books() {
    }

    public Books(int BookID, String Title, int Edition, String Publisher, int Pages, int Year, int BooksLeft, double Price) {
        this.BookID = BookID;
        this.Title = Title;
        this.Edition = Edition;
        this.Publisher = Publisher;
        this.Pages = Pages;
        this.Year = Year;
        this.BooksLeft = BooksLeft;
        this.Price = Price;
    }

    public int getBookID() {
        return BookID;
    }

    public void setBookID(int BookID) {
        this.BookID = BookID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public int getEdition() {
        return Edition;
    }

    public void setEdition(int Edition) {
        this.Edition = Edition;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String Publisher) {
        this.Publisher = Publisher;
    }

    public int getPages() {
        return Pages;
    }

    public void setPages(int Pages) {
        this.Pages = Pages;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int Year) {
        this.Year = Year;
    }

    public int getBooksLeft() {
        return BooksLeft;
    }

    public void setBooksLeft(int BooksLeft) {
        this.BooksLeft = BooksLeft;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    @Override
    public String toString() {
        return "Books{" +
                "BookID=" + BookID +
                ", Title='" + Title + '\'' +
                ", Edition=" + Edition +
                ", Publisher='" + Publisher + '\'' +
                ", Pages=" + Pages +
                ", Year=" + Year +
                ", BooksLeft=" + BooksLeft +
                ", Price=" + Price +
                '}';
    }
}
