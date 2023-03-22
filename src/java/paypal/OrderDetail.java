/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paypal;

/**
 *
 * @author thaiq
 */
public class OrderDetail {
    
    private int orderID;
    private int productID;
    private float price;
    private int quantity;
    private float totalPrice;

    public OrderDetail() {
    }

    public OrderDetail(String orderID, String productID, String price, String quantity, String totalPrice) {
        this.orderID = Integer.parseInt(orderID);
        this.productID = Integer.parseInt(productID);
        this.price = Float.parseFloat(price);
        this.quantity = Integer.parseInt(quantity);
        this.totalPrice = Float.parseFloat(totalPrice);
    }

    public String getOrderID() {
         return String.format("%d", orderID);
    }

    public String getProductID() {
        return String.format("%d", productID);
    }

    public String getPrice() {
        return String.format("%.2f", price);
    }

    public String getQuantity() {
       return String.format("%d", quantity);
    }

    public String getTotalPrice() {
        return String.format("%.2f", totalPrice);
    }
}
