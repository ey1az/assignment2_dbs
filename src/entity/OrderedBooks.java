package entity;

public class OrderedBooks {
    private Orders OrderID;
    private Books BookID;
    private int BookNum;


    public OrderedBooks() {
    }

    public OrderedBooks(Orders OrderID, Books BookID, int BookNum) {
        this.OrderID = OrderID;
        this.BookID = BookID;
        this.BookNum = BookNum;
    }

    public Orders getOrder() {
        return OrderID;
    }

    public void setOrder(Orders OrderID) {
        this.OrderID = OrderID;
    }

    public Books getBook() {
        return BookID;
    }

    public void setBook(Books BookID) {
        this.BookID = BookID;
    }

    public int getBookNum() {
        return BookNum;
    }

    public void setBookNum(int BookNum) {
        this.BookNum = BookNum;
    }

    @Override
    public String toString() {
        return "OrderedBooks{" +
                "OrderID=" + OrderID +
                ", BookID=" + BookID +
                ", BookNum=" + BookNum +
                '}';
    }
}
