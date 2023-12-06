import java.sql.SQLException;
import java.util.Scanner;

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
import metadata.MetaData;

public class Main {

    private static Scanner input = new Scanner(System.in);
    private static AuthorsFunc authorsFunc = new AuthorsFunc();
    private static BooksFunc booksFunc = new BooksFunc();
    private static CustomersFunc customersFunc = new CustomersFunc();
    private static OrdersFunc ordersFunc = new OrdersFunc();
    private static OrderedBooksFunc orderedBooksFunc = new OrderedBooksFunc();
    private static BooksAuthorsFunc booksAuthorsFunc = new BooksAuthorsFunc();
    private static TotalBooksInfoFunc totalBooksInfoFunc = new TotalBooksInfoFunc();

    public static void main(String[] args) throws SQLException {
        userIntf();
    }

    public static void userIntf() throws SQLException{
        while (true) {
            System.out.println();
            System.out.println("Choose one of the options:");
            System.out.println("""
                    1. Authors table
                    2. Books table
                    3. Customers table
                    4. Orders table
                    5. OrderedBooks table
                    6. BooksAuthors table
                    7. Total Books information
                    8. Show table names and columns
                    9. Show column details
                    10. Show primary keys
                    11. Show foreign keys
                    12. Close the app""");
            int selection = input.nextInt();
            input.nextLine();

            switch (selection) {
                case 1:
                    authorsTableOp();
                case 2:
                    booksTableOp();
                case 3:
                    customersTableOp();
                case 4:
                    ordersTableOp();
                case 5:
                    orderedBooksTableOp();
                case 6:
                    booksAuthorsTableOp();
                case 7:
                    totalBooksInfoOp();
                case 8:
                    MetaData.displayTableNamesAndColumns();
                    userIntf();
                case 9:
                    MetaData.displayColumnDetails();
                    userIntf();
                case 10:
                    MetaData.displayPrimaryKeys();
                    userIntf();
                case 11:
                    MetaData.displayForeignKeys();
                    userIntf();
                case 12:
                    closeIntf();
                default:
                    System.out.println("Invalid option.");
                    userIntf();
            }
        }
    }

    public static void authorsTableOp() throws SQLException {
        System.out.println();
        System.out.println("Choose one of the options:");
        System.out.println("""
                1. Add an author to the Authors table
                2. Get all authors from the Authors table
                3. Update an author in the Authors table
                4. Delete an author from the Authors table
                5. Get an author by AuthorID from the Authors table
                6. Go back""");
        int selection = input.nextInt();
        input.nextLine();

        switch(selection){
            case 1:
                System.out.print("Enter an AuthorID: ");
                int AuthorID = input.nextInt();
                input.nextLine();
                System.out.print("Enter an AuthorFullName: ");
                String AuthorFullName = input.nextLine();
                authorsFunc.addAuthor(new Authors(AuthorID, AuthorFullName));
                authorsTableOp();
            case 2: 
                System.out.println();
                authorsFunc.getAllAuthors();
                authorsTableOp();
            case 3:
                System.out.print("Enter an AuthorID: ");
                AuthorID = input.nextInt();
                input.nextLine();
                System.out.print("Enter an AuthorFullName: ");
                AuthorFullName = input.nextLine();
                authorsFunc.updateAuthor(new Authors(AuthorID, AuthorFullName));
                authorsTableOp();
            case 4:
                System.out.print("Enter an AuthorID: ");
                AuthorID = input.nextInt();
                authorsFunc.deleteAuthor(AuthorID);
                authorsTableOp();
            case 5:
                System.out.print("Enter an AuthorID: ");
                AuthorID = input.nextInt();
                authorsFunc.getAuthorByID(AuthorID);
                authorsTableOp();
            case 6:
                userIntf();
            default:
                System.out.println("Invalid option.");  
                authorsTableOp();
        }
    }

    public static void booksTableOp() throws SQLException {
        System.out.println();
        System.out.println("Choose one of the options:");
        System.out.println("""
                1. Add a book to the Books table
                2. Get all books from the Books table
                3. Update a book in the Books table
                4. Delete a book from the Books table
                5. Get a book by BookID from the Books table
                6. Go back""");
        int selection = input.nextInt();
        input.nextLine();

        switch(selection){
            case 1:
                System.out.print("Enter a BookID: ");
                int BookID = input.nextInt();
                input.nextLine();
                System.out.print("Enter a Title: ");
                String Title = input.nextLine();
                System.out.print("Enter an Edition: ");
                int Edition = input.nextInt();
                input.nextLine();
                System.out.print("Enter a Publisher: ");
                String Publisher = input.nextLine();
                System.out.print("Enter Pages number: ");
                int Pages = input.nextInt();
                System.out.print("Enter a Year: ");
                int Year = input.nextInt();   
                System.out.print("Enter a number of Books Left: ");
                int BooksLeft = input.nextInt();
                System.out.print("Enter a Price: ");
                double Price = input.nextDouble();       
                booksFunc.addBook(new Books(BookID, Title, Edition, Publisher, Pages, Year, BooksLeft, Price));
                booksTableOp();                                                                                   
            case 2: 
                System.out.println();
                booksFunc.getAllBooks();
                booksTableOp();
            case 3:
                System.out.print("Enter a BookID: ");
                BookID = input.nextInt();
                input.nextLine();
                System.out.print("Enter a Title: ");
                Title = input.nextLine();
                System.out.print("Enter an Edition: ");
                Edition = input.nextInt();
                input.nextLine();
                System.out.print("Enter a Publisher: ");
                Publisher = input.nextLine();
                System.out.print("Enter Pages number: ");
                Pages = input.nextInt();
                System.out.print("Enter a Year: ");
                Year = input.nextInt();   
                System.out.print("Enter a number of Books Left: ");
                BooksLeft = input.nextInt();
                System.out.print("Enter a Price: ");
                Price = input.nextDouble();       
                booksFunc.updateBook(new Books(BookID, Title, Edition, Publisher, Pages, Year, BooksLeft, Price));
                booksTableOp();     
            case 4:
                System.out.print("Enter a BookID: ");
                BookID = input.nextInt();
                booksFunc.deleteBook(BookID);
                booksTableOp();
            case 5:
                System.out.print("Enter a BookID: ");
                BookID = input.nextInt();
                booksFunc.getBookByID(BookID);
                booksTableOp();
            case 6:
                userIntf();
            default:
                System.out.println("Invalid option.");  
                booksTableOp();
        }        
    }

    public static void customersTableOp() throws SQLException {
        System.out.println();
        System.out.println("Choose one of the options:");
        System.out.println("""
                1. Add a customer to the Customers table
                2. Get all customers from the Customers table
                3. Update a customer in the Customers table
                4. Delete a customer from the Customers table
                5. Get a customer by CustomerID from the Customers table
                6. Go back""");
        int selection = input.nextInt();
        input.nextLine();

        switch(selection){
            case 1:
                System.out.print("Enter a CustomerID: ");
                int CustomerID = input.nextInt();
                input.nextLine();
                System.out.print("Enter a CustomerFullName: ");
                String CustomerFullName = input.nextLine();
                System.out.print("Enter a CustomerCity: ");
                String CustomerCity = input.nextLine();                
                customersFunc.addCustomer(new Customers(CustomerID, CustomerFullName, CustomerCity));
                customersTableOp();
            case 2: 
                System.out.println();
                customersFunc.getAllCustomers();
                customersTableOp();
            case 3:
                System.out.print("Enter a CustomerID: ");
                CustomerID = input.nextInt();
                input.nextLine();
                System.out.print("Enter a CustomerFullName: ");
                CustomerFullName = input.nextLine();
                System.out.print("Enter a CustomerCity: ");
                CustomerCity = input.nextLine();                
                customersFunc.updateCustomer(new Customers(CustomerID, CustomerFullName, CustomerCity));
                customersTableOp();
            case 4:
                System.out.print("Enter a CustomerID: ");
                CustomerID = input.nextInt();
                customersFunc.deleteCustomer(CustomerID);
                customersTableOp();
            case 5:
                System.out.print("Enter a CustomerID: ");
                CustomerID = input.nextInt();
                customersFunc.getCustomersByID(CustomerID);
                customersTableOp();
            case 6:
                userIntf();
            default:
                System.out.println("Invalid option.");  
                customersTableOp();
        }
    }

    public static void ordersTableOp() throws SQLException {
        System.out.println();
        System.out.println("Choose one of the options:");
        System.out.println("""
                1. Add an order to the Orders table
                2. Get all orders from the Orders table
                3. Update an order in the Orders table
                4. Delete an order from the Orders table
                5. Get an order by OrderID from the Orders table
                6. Go back""");
        int selection = input.nextInt();
        input.nextLine();

        switch(selection){
            case 1:
                System.out.print("Enter an OrderID: ");
                int OrderID = input.nextInt();
                System.out.print("Enter a CustomerID: ");
                int CustomerID = input.nextInt();            
                ordersFunc.addOrder(new Orders(OrderID, customersFunc.getCustomersByID(CustomerID)));
                ordersTableOp();
            case 2: 
                System.out.println();
                ordersFunc.getAllOrders();
                ordersTableOp();
            case 3:
                System.out.print("Enter an OrderID: ");
                OrderID = input.nextInt();
                System.out.print("Enter a CustomerID: ");
                CustomerID = input.nextInt();            
                ordersFunc.updateOrder(new Orders(OrderID, customersFunc.getCustomersByID(CustomerID)));
                ordersTableOp();
            case 4:
                System.out.print("Enter an OrderID: ");
                OrderID = input.nextInt();
                ordersFunc.deleteOrder(OrderID);
                ordersTableOp();
            case 5:
                System.out.print("Enter an OrderID: ");
                OrderID = input.nextInt();
                ordersFunc.getOrderByID(OrderID);
                ordersTableOp();
            case 6:
                userIntf();
            default:
                System.out.println("Invalid option.");  
                ordersTableOp();
        }
    }

    public static void orderedBooksTableOp() throws SQLException {
        System.out.println();
        System.out.println("Choose one of the options:");
        System.out.println("""
                1. Add order details to the OrderedBooks table
                2. Get all order details from the OrderedBooks table
                3. Update order details in the OrderedBooks table
                4. Delete order details from the OrderedBooks table
                5. Get order details by OrderID and BookID from the OrderedBooks table
                6. Go back""");
        int selection = input.nextInt();
        input.nextLine();

        switch(selection){
            case 1:
                System.out.print("Enter an OrderID: ");
                int OrderID = input.nextInt();
                System.out.print("Enter a BookID: ");
                int BookID = input.nextInt();   
                System.out.print("Enter BookNum: ");
                int BookNum = input.nextInt();              
                orderedBooksFunc.addOrderedBook(new OrderedBooks(ordersFunc.getOrderByID(OrderID), booksFunc.getBookByID(BookID), BookNum));
                orderedBooksTableOp();
            case 2: 
                System.out.println();
                orderedBooksFunc.getAllOrderedBooks();
                orderedBooksTableOp();
            case 3:
                System.out.print("Enter an OrderID: ");
                OrderID = input.nextInt();
                System.out.print("Enter a BookID: ");
                BookID = input.nextInt();   
                System.out.print("Enter BookNum: ");
                BookNum = input.nextInt();              
                orderedBooksFunc.updateOrderedBook(new OrderedBooks(ordersFunc.getOrderByID(OrderID), booksFunc.getBookByID(BookID), BookNum));
                orderedBooksTableOp();
            case 4:
                System.out.print("Enter an OrderID: ");
                OrderID = input.nextInt();
                System.out.print("Enter a BookID: ");
                BookID = input.nextInt();   
                orderedBooksFunc.deleteOrderedBook(OrderID, BookID);
                orderedBooksTableOp();
            case 5:
                System.out.print("Enter an OrderID: ");
                OrderID = input.nextInt();
                System.out.print("Enter a BookID: ");
                BookID = input.nextInt();   
                orderedBooksFunc.getOrderedBookAndOrderByID(OrderID, BookID);
                orderedBooksTableOp();
            case 6:
                userIntf();
            default:
                System.out.println("Invalid option.");  
                orderedBooksTableOp();
        }
    }

    public static void booksAuthorsTableOp() throws SQLException {
        System.out.println();
        System.out.println("Choose one of the options:");
        System.out.println("""
                1. Add book and author details to the BooksAuthors table
                2. Get all book and author details from the BooksAuthors table
                3. Update book and author details in the BooksAuthors table
                4. Delete book and author details from the BooksAuthors table
                5. Get book and author details by AuthorID and BookID from the BooksAuthors table
                6. Go back""");
        int selection = input.nextInt();
        input.nextLine();

        switch(selection){
            case 1:
                System.out.print("Enter an AuthorID: ");
                int AuthordID = input.nextInt();
                System.out.print("Enter a BookID: ");
                int BookID = input.nextInt();            
                booksAuthorsFunc.addBookAuthor(new BooksAuthors(authorsFunc.getAuthorByID(AuthordID), booksFunc.getBookByID(BookID)));
                booksAuthorsTableOp();
            case 2: 
                System.out.println();
                booksAuthorsFunc.getAllBooksAuthors();
                booksAuthorsTableOp();
            case 3:
                System.out.print("Enter an AuthorID: ");
                AuthordID = input.nextInt();
                System.out.print("Enter a BookID: ");
                BookID = input.nextInt();            
                booksAuthorsFunc.updateBookAuthor(new BooksAuthors(authorsFunc.getAuthorByID(AuthordID), booksFunc.getBookByID(BookID)));
                booksAuthorsTableOp();
            case 4:
                System.out.print("Enter an AuthorID: ");
                AuthordID = input.nextInt();
                System.out.print("Enter a BookID: ");
                BookID = input.nextInt();            
                booksAuthorsFunc.deleteBookAuthor(AuthordID, BookID);
                booksAuthorsTableOp();
            case 5:
                System.out.print("Enter an AuthorID: ");
                AuthordID = input.nextInt();
                System.out.print("Enter a BookID: ");
                BookID = input.nextInt();            
                booksAuthorsFunc.getBookAndAuthorByID(AuthordID, BookID);
                booksAuthorsTableOp();
            case 6:
                userIntf();
            default:
                System.out.println("Invalid option.");  
                booksAuthorsTableOp();
        }
    }

    public static void totalBooksInfoOp() throws SQLException {
        System.out.println();
        System.out.println("Choose one of the options:");
        System.out.println("""
                1. Show total book information
                2. Go back""");
        int selection = input.nextInt();
        input.nextLine();

        switch(selection){
            case 1:
                totalBooksInfoFunc.getTotalBooksInfo();
                userIntf();
            case 2:
                userIntf();
            default:
                System.out.println("Invalid option.");  
                totalBooksInfoOp();
        }
    }

    public static void closeIntf() throws SQLException {
        System.out.println();
        System.out.println("Type `exit` to close the app. Any other input will lead you back to the app.");

        if (input.nextLine().equalsIgnoreCase("exit")) {
            System.exit(0);
        } else {
            userIntf();
        }
    }
}
