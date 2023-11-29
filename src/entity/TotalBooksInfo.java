package entity;

public class TotalBooksInfo {
    private Books Book;
    private Authors Authors;
    private Orders Orders;
    private OrderedBooks OrderedBooks;

    public TotalBooksInfo() {
    }

    public TotalBooksInfo(Books Book, Authors Authors, Orders Orders, OrderedBooks OrderedBooks) {
        this.Book = Book;
        this.Authors = Authors;
        this.Orders = Orders;
        this.OrderedBooks = OrderedBooks;
    }

    public Books getBook() {
        return Book;
    }

    public void setBook(Books Book) {
        this.Book = Book;
    }

    public Authors getAuthors() {
        return Authors;
    }

    public void setAuthors(Authors Authors) {
        this.Authors = Authors;
    }

    public Orders getOrders() {
        return Orders;
    }

    public void setOrders(Orders Orders) {
        this.Orders = Orders;
    }

    public OrderedBooks getOrderedBooks() {
        return OrderedBooks;
    }

    public void setOrderedBooks(OrderedBooks OrderedBooks) {
        this.OrderedBooks = OrderedBooks;
    }
    @Override
    public String toString() {
        return "BookID: " + Book.getBookID() +
               ", Title: " + Book.getTitle() +
               ", Edition: " + Book.getEdition() +
               ", AuthorID: " + Authors.getAuthorId() +
               ", AuthorFullName: " + Authors.getAuthorFullName() +
               ", OrderID: " + Orders.getOrderID() +
               ", CustomerID: " + Orders.getCustomer().getCustomerID() +
               ", BookNum: " + OrderedBooks.getBookNum();
    }
}
