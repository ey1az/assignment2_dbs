package access;

import entity.Orders;
import java.util.List;

public interface OrdersAccess {
    public boolean addOrder(Orders Order);
    public List<Orders> getAllOrders();
    public boolean updateOrder(Orders Order);
    public boolean deleteOrder(int OrderID);
    public Orders getOrderByID(int OrderID);
}