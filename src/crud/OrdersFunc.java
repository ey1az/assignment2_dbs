package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.DBSCon;
import access.OrdersAccess;
import entity.Customers;
import entity.Orders;

public class OrdersFunc extends DBSCon implements OrdersAccess {

    @Override
    public boolean addOrder(Orders Order) {
        try (Connection connection = connect()) {
            try (PreparedStatement stmnt = connection.prepareStatement("INSERT INTO Orders (OrderID, CustomerID, FullPrice) VALUES (?,?,?)")) {
                stmnt.setInt(1, Order.getOrderID());
                stmnt.setInt(2, Order.getCustomer().getCustomerID());
                stmnt.setDouble(3, Order.getFullPrice());
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
    public List<Orders> getAllOrders() {
        List<Orders> OrdersList = new ArrayList<>();
        try (Connection connection = connect()) {
            try (Statement stmnt = connection.createStatement()) {
                stmnt.execute("SELECT * FROM Orders");
                ResultSet res_set = stmnt.getResultSet();
                while (res_set.next()) {
                    int OrderID = res_set.getInt("OrderID");
                    int CustomerID = res_set.getInt("CustomerID");
                    Customers Customer = new Customers(CustomerID, "", "");
                    double FullPrice = res_set.getDouble("FullPrice");

                    Orders Order = new Orders(OrderID, Customer, FullPrice);
                    OrdersList.add(Order);
                    System.out.println("Order ID: " + OrderID + ", Customer ID: " + Customer.getCustomerID() + ", Full Price: " + FullPrice);
                }
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.out.println("SQL Error! " + sqle.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error! " + e.getMessage());
        }

        return OrdersList;
    }

    @Override
    public boolean updateOrder(Orders Order) {
        try (Connection connection = connect()) {
            try (PreparedStatement stmnt = connection.prepareStatement("UPDATE Orders SET CustomerID=?, FullPrice=? WHERE OrderID=?")) {
                stmnt.setInt(1, Order.getCustomer().getCustomerID());
                stmnt.setDouble(2, Order.getFullPrice());
                stmnt.setInt(3, Order.getOrderID());
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
    public boolean deleteOrder(int OrderID) {
        try (Connection connection = connect()) {
            try (Statement stmnt = connection.createStatement()) {
                stmnt.execute("DELETE FROM Orders WHERE OrderID = '" + OrderID + "'");
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
    public Orders getOrderByID(int OrderID) {
        Orders Order = null;
        try (Connection connection = connect()) {
            try (Statement stmnt = connection.createStatement()) {
                stmnt.execute("SELECT * FROM Orders WHERE OrderID = '" + OrderID + "'");
                ResultSet res_set = stmnt.getResultSet();
                while (res_set.next()) {
                    int RetrievedOrderID = res_set.getInt("OrderID");
                    int CustomerID = res_set.getInt("CustomerID");
                    Customers Customer = new Customers(CustomerID, "", "");
                    double FullPrice = res_set.getDouble("FullPrice");

                    Order = new Orders(RetrievedOrderID, Customer, FullPrice);
                    System.out.println("Order ID: " + RetrievedOrderID + ", Customer ID: " + Customer.getCustomerID() + ", Full Price: " + FullPrice);
                }
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.out.println("SQL Error! " + sqle.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error! " + e.getMessage());
        }

        if (Order == null) {
            System.out.println("No such OrderID found.");
        }     

        return Order;
    }
}
