package poly.duan.fastfoodie.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {
    @SerializedName("userid")
    private String userId;
    @SerializedName("username")
    private String username;
    @SerializedName("email")
    private String email;
    @SerializedName("phone")
    private int phone;
    @SerializedName("password")
    private String password;

    public User() {
    }

    public User(String userId, String username, String email, int phone, String password) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
