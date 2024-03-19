package poly.duan.fastfoodie.Model;

public class Product {
    private int id;
    private String productname;
    private String description;
    private int price;
    private String imageproduct;

    public Product() {
    }

    public Product(int id, String productname, String description, int price, String imageproduct) {
        this.id = id;
        this.productname = productname;
        this.description = description;
        this.price = price;
        this.imageproduct = imageproduct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImageproduct() {
        return imageproduct;
    }

    public void setImageproduct(String imageproduct) {
        this.imageproduct = imageproduct;
    }
}
