package entity;

public class Customers {
    private int CustomerID;
    private String CustomerFullName;
    private String CustomerCity;

    public Customers() {
    }

    public Customers(int CustomerID, String CustomerFullName, String CustomerCity) {
        this.CustomerID = CustomerID;
        this.CustomerFullName = CustomerFullName;
        this.CustomerCity = CustomerCity;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int CustomerID) {
        this.CustomerID = CustomerID;
    }

    public String getCustomerFullName() {
        return CustomerFullName;
    }

    public void setCustomerFullName(String CustomerFullName) {
        this.CustomerFullName = CustomerFullName;
    }

    public String getCustomerCity() {
        return CustomerCity;
    }

    public void setCustomerCity(String CustomerCity) {
        this.CustomerCity= CustomerCity;
    }

    @Override
    public String toString() {
        return "Author{" +
                "CustomerID=" + CustomerID +
                ", CustomerFullName=" + CustomerFullName +
                ", CustomerCity=" + CustomerCity +
                '}';
    }
}
