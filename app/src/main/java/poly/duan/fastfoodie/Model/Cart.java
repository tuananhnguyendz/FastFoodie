package poly.duan.fastfoodie.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Cart implements Serializable {
    @SerializedName("_id")

    private String userId;
    @SerializedName("productId")

    private String productId;
    @SerializedName("price")

    private double price;
    @SerializedName("totalOder")

    private double totalOder;
    @SerializedName("quantity")

    private int quantity;

    public Cart() {
        this.userId = userId;
        this.productId = productId;
        this.price = price;
        this.totalOder = totalOder;
        this.quantity = quantity;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalOder() {
        return totalOder;
    }

    public void setTotalOder(double totalOder) {
        this.totalOder = totalOder;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
