package entity;

public class Orders {
    private int OrderID;
    private Customers CustomerID;
    private int BookNum;
    private double FullPrice;

    public Orders() {
    }

    public Orders(int OrderID, Customers CustomerID, int BookNum, double FullPrice) {
        this.OrderID = OrderID;
        this.CustomerID = CustomerID;
        this.BookNum = BookNum;
        this.FullPrice = FullPrice;
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

    public int getBookNum() {
        return BookNum;
    }

    public void setBookNum(int BookNum) {
        this.BookNum = BookNum;
    }

    public double getFullPrice() {
        return FullPrice;
    }

    public void setFullPrice(double FullPrice) {
        this.FullPrice = FullPrice;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "OrderID='" + OrderID + '\'' +
                ", CustomerID=" + CustomerID +
                ", BookNum=" + BookNum +
                ", FullPrice=" + FullPrice +
                '}';
    }
}
