package poly.duan.fastfoodie.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import poly.duan.fastfoodie.Model.Cart;
import poly.duan.fastfoodie.Model.CartResponse;
import poly.duan.fastfoodie.Model.Product;
import poly.duan.fastfoodie.Model.WithList;
import poly.duan.fastfoodie.R;
import poly.duan.fastfoodie.Service.ApiService;
import poly.duan.fastfoodie.Service.ProductService;
import poly.duan.fastfoodie.databinding.ActivityDetailBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
//    private Product product01;
    private int quantity = 1;

    ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Product product = (Product) getIntent().getSerializableExtra("productId");

//        Log.d("DetailActivity", "ID sản phẩm: " + product.getId()); // Thêm log này để kiểm tra tên ID sản phẩm
        binding.txtProductnameDetail.setText(product.getProductname());
        binding.txtPriceDetail.setText(String.valueOf(product.getPrice()));
        binding.txtDescriptionDetail.setText(product.getDescription());

        binding.txtTotal.setText(String.valueOf(product.getPrice()));

        binding.btnFavourite.setOnClickListener(v -> {
            addToWithList();
        });
        binding.imgBackDetail.setOnClickListener(v -> {
        finish();
        });

        binding.btnAddtocart.setOnClickListener(v -> {
            //thêm vào giỏ hàng
        });



        binding.plusBtn.setOnClickListener(v -> {
            quantity = quantity + 1;
            binding.txtNumTotal.setText(quantity + "");
            binding.txtTotal.setText(String.valueOf(quantity * product.getPrice()));
        });

        binding.minBtn.setOnClickListener(v -> {
            if (quantity > 1) {
                quantity = quantity - 1;
                binding.txtNumTotal.setText(quantity + "");
                binding.txtTotal.setText(quantity * product.getPrice());
            }
        });
        
        binding.btnAddtocart.setOnClickListener(v -> {
            addToCart();
        });
    }


    // Phương thức để tìm ID tài nguyên ảnh dựa trên chuỗi ID

    private void addToWithList(){
        SharedPreferences sharedPreferences = getSharedPreferences("myPre", MODE_PRIVATE);
        String userId = sharedPreferences.getString("userId", "-1");

        Product product = (Product) getIntent().getSerializableExtra("productId");
        String productId = product.getId();


        WithList withList = new WithList(userId,productId);

        ApiService.api.addToWithList(withList).enqueue(new Callback<WithList>() {
            @Override
            public void onResponse(Call<WithList> call, Response<WithList> response) {
                if(response.isSuccessful()){
                    Toast.makeText(DetailActivity.this, "Đã thêm sản phẩm vào danh sách yêu thích của bạn", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(DetailActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<WithList> call, Throwable t) {
                Log.d("Lỗi","Lỗi : " +t.getMessage());
                Toast.makeText(DetailActivity.this, "Đã có lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addToCart(){
        SharedPreferences sharedPreferences = getSharedPreferences("myPre", MODE_PRIVATE);
        String userId = sharedPreferences.getString("userId", "-1");

        Product product = (Product) getIntent().getSerializableExtra("productId");
        String productId = product.getId();
        int num = Integer.parseInt(binding.txtNumTotal.getText().toString());
        double price = Double.valueOf((binding.txtPriceDetail.getText().toString()));
        double total_order = Double.valueOf((binding.txtTotal.getText().toString()));
        Log.d("userId","userId :" +userId);
        Log.d("pId","pId :" +productId);
        Log.d("num","num :" +num);
        Log.d("price","price :" +price);
        Log.d("total","total :" +total_order);

        Cart cart = new Cart(userId, productId,price,total_order, quantity);

        ApiService.api.addToCart(cart).enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {

                   if(response.isSuccessful()){
                       CartResponse cartResponse = (CartResponse) response.body();

                       Toast.makeText(DetailActivity.this, "ok", Toast.LENGTH_SHORT).show();
                   }else {
                       Toast.makeText(DetailActivity.this, "Lỗi"+response.errorBody(), Toast.LENGTH_SHORT).show();
                       Log.d("dot", "onResponse: "+response.errorBody());
                   }
            }

            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {
                Log.d("Tag", "onFailure: " + t.getMessage());
            }
        });
    }
}