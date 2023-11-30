package connection;

import crud.AuthorsFunc;
import crud.BooksFunc;
import crud.CustomersFunc;
import crud.OrderedBooksFunc;
import crud.OrdersFunc;
import crud.BooksAuthorsFunc;
import crud.TotalBooksInfoFunc;
import entity.Authors;
import entity.Books;
import entity.Customers;
import entity.OrderedBooks;
import entity.Orders;
import entity.BooksAuthors;
import entity.TotalBooksInfo;
import metadata.MetaData;

public class Main {
    public static void main(String[] args) {
        AuthorsFunc AuthorsFunc = new AuthorsFunc();
        BooksFunc BooksFunc = new BooksFunc();
        CustomersFunc CustomersFunc = new CustomersFunc();
        OrdersFunc OrdersFunc = new OrdersFunc();
        OrderedBooksFunc OrderedBooksFunc = new OrderedBooksFunc();
        BooksAuthorsFunc BooksAuthorsFunc = new BooksAuthorsFunc();
        TotalBooksInfo TotalBooksInfo = new TotalBooksInfo();
        

        Authors newAuthor = new Authors(5, "Kole Watson");

        // AuthorsFunc.addAuthor(newAuthor);
        // AuthorsFunc.deleteAuthor(5);
        // AuthorsFunc.updateAuthor(newAuthor);
        // AuthorsFunc.getAuthorByID(4);
        // AuthorsFunc.getAllAuthors();

        Books newBook = new Books(3, "Python Guide", 6, "Kate Je", 107, 2019, 16, 49.99);

        // BooksFunc.addBook(newBook);
        // BooksFunc.deleteBook(3);
        // BooksFunc.updateBook(newBook);
        // BooksFunc.getBookByID(3);
        // BooksFunc.getAllBooks();

        Customers newCustomer = new Customers(5, "John Smith", "Kyoto");

        // CustomersFunc.addCustomer(newCustomer);
        // CustomersFunc.deleteCustomer(5);
        // CustomersFunc.updateCustomer(newCustomer);
        // CustomersFunc.getCustomersByID(5);
        // CustomersFunc.getAllCustomers();
            
        Orders newOrder = new Orders(15, newCustomer);

        // OrdersFunc.addOrder(newOrder);
        // OrdersFunc.deleteOrder(15);
        // OrdersFunc.updateOrder(newOrder);
        // OrdersFunc.getOrderByID(12);
        // OrdersFunc.getAllOrders();

        OrderedBooks newOrderedBooks = new OrderedBooks(newOrder, newBook, 30);

        // OrderedBooksFunc.addOrderedBook(newOrderedBooks);
        // OrderedBooksFunc.deleteOrderedBook(15, 3);
        // OrderedBooksFunc.updateOrderedBook(newOrderedBooks);
        // OrderedBooksFunc.getOrderedBookAndOrderByID(15, 3);
        // OrderedBooksFunc.getAllOrderedBooks();

        BooksAuthors newBookAuthor = new BooksAuthors(newAuthor, newBook);

        // BooksAuthorsFunc.addBookAuthor(newBookAuthor);
        // BooksAuthorsFunc.deleteBookAuthor(4, 3);
        // BooksAuthorsFunc.updateBookAuthor(newBookAuthor);
        // BooksAuthorsFunc.getBookAndAuthorByID(4, 2);
        // BooksAuthorsFunc.getAllBooksAuthors();

        TotalBooksInfoFunc totalBooksInfoFunc = new TotalBooksInfoFunc();
        // totalBooksInfoFunc.getTotalBooksInfo();

        
        // try {
        //     MetaData.displayTableNamesAndColumns();
        // } catch (SQLException e) {
        //     e.printStackTrace();
        // }

        // try {
        //     MetaData.displayColumnDetails();
        // } catch (SQLException e) {
        //     e.printStackTrace();
        // }
  
        // try {
        //     MetaData.displayPrimaryKeys();
        // } catch (SQLException e) {
        //     e.printStackTrace();
        // }
  
        // try {
        //     MetaData.displayForeignKeys();
        // } catch (SQLException e) {
        //     e.printStackTrace();
        // }

    }
}
