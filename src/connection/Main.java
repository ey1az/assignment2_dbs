package connection;

import java.sql.SQLException;

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
        

        Authors newAuthor = new Authors(1, "Alex Oe");

        // AuthorsFunc.addAuthor(newAuthor);
        // AuthorsFunc.deleteAuthor(1);
        // AuthorsFunc.updateAuthor(newAuthor);
        // AuthorsFunc.getAuthorByID(1);
        // AuthorsFunc.getAllAuthors();

        Books newBook = new Books(1, "Python Guide", 12, "Kate Je", 107, 2019, 16, 49.99);

        // BooksFunc.addBook(newBook);
        // BooksFunc.deleteBook(1);
        // BooksFunc.updateBook(newBook);
        // BooksFunc.getBookByID(1);
        // BooksFunc.getAllBooks();

        Customers newCustomer = new Customers(1, "John Smith", "Kyoto");

        // CustomersFunc.addCustomer(newCustomer);
        // CustomersFunc.deleteCustomer(5);
        // CustomersFunc.updateCustomer(newCustomer);
        // CustomersFunc.getCustomersByID(2);
        // CustomersFunc.getAllCustomers();
            
        Orders newOrder = new Orders(1, newCustomer);

        // OrdersFunc.addOrder(newOrder);
        // OrdersFunc.deleteOrder(1);
        // OrdersFunc.updateOrder(newOrder);
        // OrdersFunc.getOrderByID(1);
        // OrdersFunc.getAllOrders();

        OrderedBooks newOrderedBooks = new OrderedBooks(newOrder, newBook, 1);

        // OrderedBooksFunc.addOrderedBook(newOrderedBooks);
        // OrderedBooksFunc.deleteOrderedBook(1, 2);
        // OrderedBooksFunc.updateOrderedBook(newOrderedBooks);
        // OrderedBooksFunc.getOrderedBookAndOrderByID(1, 1);
        // OrderedBooksFunc.getAllOrderedBooks();

        BooksAuthors newBookAuthor = new BooksAuthors(newAuthor, newBook);

        // BooksAuthorsFunc.addBookAuthor(newBookAuthor);
        // BooksAuthorsFunc.deleteBookAuthor(2, 2);
        // BooksAuthorsFunc.updateBookAuthor(newBookAuthor);
        // BooksAuthorsFunc.getBookAndAuthorByID(2, 1);
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
