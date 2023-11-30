package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.DBSCon;
import access.CustomersAccess;
import entity.Customers;

public class CustomersFunc extends DBSCon implements CustomersAccess {

    @Override
    public boolean addCustomer(Customers Customer) {
        try (Connection connection = connect()) {
            try (PreparedStatement stmnt = connection.prepareStatement("INSERT INTO Customers (CustomerID, CustomerFullName, CustomerCity) VALUES (?,?,?)")) {
                stmnt.setInt(1, Customer.getCustomerID());
                stmnt.setString(2, Customer.getCustomerFullName());
                stmnt.setString(3, Customer.getCustomerCity());
                return stmnt.execute();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.out.println("SQL Error! " + sqle.getMessage());
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error! " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Customers> getAllCustomers() {
        List<Customers> CustomersList = new ArrayList<>();
        try (Connection connection = connect()) {
            try (Statement stmnt = connection.createStatement()) {
                stmnt.execute("SELECT * FROM Customers");
                ResultSet res_set = stmnt.getResultSet();
                while (res_set.next()) {
                    int CustomerID = res_set.getInt("CustomerID");
                    String CustomerFullName = res_set.getString("CustomerFullName");
                    String CustomerCity = res_set.getString("CustomerCity");

                    Customers Customer = new Customers(CustomerID, CustomerFullName, CustomerCity);
                    CustomersList.add(Customer);
                    System.out.println("Customer ID: " + CustomerID + ", Full Name: " + CustomerFullName + ", City: " + CustomerCity);
                }
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.out.println("SQL Error! " + sqle.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error! " + e.getMessage());
        }

        return CustomersList;
    }

    @Override
    public boolean updateCustomer(Customers Customer) {
        try (Connection connection = connect()) {
            Customers existingCustomer = getCustomersByID(Customer.getCustomerID());

            if (existingCustomer == null) {
                return false;
            }

            try (PreparedStatement stmnt = connection.prepareStatement("UPDATE Customers SET CustomerFullName=?, CustomerCity=? WHERE CustomerID=?")) {
                stmnt.setString(1, Customer.getCustomerFullName());
                stmnt.setString(2, Customer.getCustomerCity());
                stmnt.setInt(3, Customer.getCustomerID());
                return stmnt.execute();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.out.println("SQL Error! " + sqle.getMessage());
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error! " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteCustomer(int CustomerID) {
        try (Connection connection = connect()) {
            Customers existingCustomer = getCustomersByID(CustomerID);

            if (existingCustomer == null) {
                return false;
            }

            try (Statement stmnt = connection.createStatement()) {
                stmnt.execute("DELETE FROM Customers WHERE CustomerID = " + CustomerID);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.out.println("SQL Error! " + sqle.getMessage());
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error! " + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public Customers getCustomersByID(int CustomerID) {
        Customers Customer = null;
        try (Connection connection = connect()) {
            try (Statement stmnt = connection.createStatement()) {
                stmnt.execute("SELECT * FROM Customers WHERE CustomerID = " + CustomerID);
                ResultSet res_set = stmnt.getResultSet();
                while (res_set.next()) {
                    int RetrievedCustomerID = res_set.getInt("CustomerID");
                    String CustomerFullName = res_set.getString("CustomerFullName");
                    String CustomerCity = res_set.getString("CustomerCity");

                    Customer = new Customers(RetrievedCustomerID, CustomerFullName, CustomerCity);
                    System.out.println("Customer ID: " + RetrievedCustomerID + ", Full Name: " + CustomerFullName + ", City: " + CustomerCity);
                }
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.out.println("SQL Error! " + sqle.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error! " + e.getMessage());
        }

        if (Customer == null) {
            System.out.println("No such CustomerID found.");
        }     

        return Customer;
    }
}
