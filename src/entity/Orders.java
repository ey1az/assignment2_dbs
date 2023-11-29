package entity;

public class Orders {
    private int OrderID;
    private Customers CustomerID;

    public Orders() {
    }

    public Orders(int OrderID, Customers CustomerID) {
        this.OrderID = OrderID;
        this.CustomerID = CustomerID;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public Customers getCustomer() {
        return CustomerID;
    }

    public void setCustomer(Customers CustomerID) {
        this.CustomerID = CustomerID;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "OrderID='" + OrderID + '\'' +
                ", CustomerID=" + CustomerID +
                '}';
    }
}
