package poly.duan.fastfoodie.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import poly.duan.fastfoodie.Adapter.CartAdapter;
import poly.duan.fastfoodie.Model.Cart;
import poly.duan.fastfoodie.Model.CartItem;
import poly.duan.fastfoodie.Model.CartResponse;
import poly.duan.fastfoodie.R;
import poly.duan.fastfoodie.Service.ApiService;
import poly.duan.fastfoodie.databinding.ActivityCartBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {
    ActivityCartBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        getCart();

    }

    private void getCart() {
        SharedPreferences sharedPreferences = getSharedPreferences("myPre", MODE_PRIVATE);
        String userId = sharedPreferences.getString("userId", "-1");
        Log.d("ID nayf5555", "onResponse: " + userId);

        Cart cart = new Cart();
        cart.setUserId(userId);
        ApiService.api.getCart(cart).enqueue(new Callback<List<CartItem>>() {
            @Override
            public void onResponse(Call<List<CartItem>> call, Response<List<CartItem>> response) {
                if (response.isSuccessful()) {

                    List<CartItem> cartItemList = response.body();
                    CartAdapter adapter = new CartAdapter(cartItemList);
                    calculateTotal(cartItemList);
                    binding.recyclerViewCart.setAdapter(adapter);
                    binding.recyclerViewCart.setLayoutManager(new LinearLayoutManager(CartActivity.this, RecyclerView.VERTICAL, false));
                    Toast.makeText(CartActivity.this, "Call OK", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CartActivity.this, "Lỗi" + response.errorBody(), Toast.LENGTH_SHORT).show();
                    Log.d("dot", "onResponse: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<CartItem>> call, Throwable t) {
                Toast.makeText(CartActivity.this, "Lỗi" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("dot", "onResponse: " + t.getMessage());
            }
        });

    }

    private void calculateTotal(List<CartItem> cartItemList) {
        int totalQuantity = 0;
        int totalPrice = 0;

        for (CartItem item : cartItemList) {
            totalQuantity += item.getQuantity();
            totalPrice += item.getPrice() * item.getQuantity();
        }
        binding.toTalCart.setText(String.valueOf(totalPrice));
        // Hiển thị kết quả hoặc sử dụng cho mục đích khác
        Log.d("Total Quantity", "Total Quantity: " + totalQuantity);
        Log.d("Total Price", "Total Price: " + totalPrice);
    }
}