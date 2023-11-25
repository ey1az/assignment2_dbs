package entity;

public class OrderedBooks {
    private Orders OrderID;
    private Books BookID;

    public OrderedBooks() {
    }

    public OrderedBooks(Orders OrderID, Books BookID) {
        this.OrderID = OrderID;
        this.BookID = BookID;
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

    @Override
    public String toString() {
        return "OrderedBooks{" +
                "OrderID=" + OrderID +
                ", BookID=" + BookID +
                '}';
    }
}
