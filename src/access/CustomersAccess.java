package access;

import entity.Customers;
import java.util.List;

public interface CustomersAccess {
    public boolean addCustomer(Customers Customer);
    public List<Customers> getAllCustomers();
    public boolean updateCustomer(Customers Customer);
    public boolean deleteCustomer(int CustomerID);
    public Customers getCustomersByID(int CustomerID);
}