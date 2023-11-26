package access;

import entity.OrderedBooks;
import java.util.List;

public interface OrderedBooksAccess {
    public boolean addOrderedBook(OrderedBooks OrderedBooks);
    public List<OrderedBooks> getAllOrderedBooks();
    public boolean updateOrderedBook(OrderedBooks OrderedBooks);
    public boolean deleteOrderedBook(int OrderID, int BookID);
    public OrderedBooks getOrderedBookAndOrderByID(int OrderID,int BookID);
}