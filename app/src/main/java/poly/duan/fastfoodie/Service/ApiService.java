package poly.duan.fastfoodie.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import poly.duan.fastfoodie.Model.Cart;
import poly.duan.fastfoodie.Model.CartResponse;
import poly.duan.fastfoodie.Model.User;
import poly.duan.fastfoodie.Model.WithList;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    ApiService api = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService.class);

    @POST("api/register")
    Call<User> register(@Body User user);

    @POST("api/login")
    Call<User> login(@Body User user);

    @POST("addToWithList")
    Call<WithList> addToWithList(@Body WithList withList);


    @POST("addToCart")
    Call<CartResponse> addToCart(@Body Cart cart);
}
