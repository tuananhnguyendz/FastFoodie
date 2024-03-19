package poly.duan.fastfoodie.Model;

public class Category {
private int Img;
private String Name;

    public Category() {
    }

    public Category(int img, String name) {
        Img = img;
        Name = name;
    }

    public int getImg() {
        return Img;
    }

    public void setImg(int img) {
        Img = img;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
