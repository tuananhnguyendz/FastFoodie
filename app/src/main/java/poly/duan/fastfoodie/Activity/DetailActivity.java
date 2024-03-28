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
            binding.txtTotal.setText(quantity * product.getPrice());
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
        double total = Double.parseDouble((binding.txtTotal.getText().toString()));
        double price = Double.parseDouble((binding.txtPriceDetail.getText().toString()));

        Log.d("userId","userId :" +userId);
        Log.d("pId","pId :" +productId);
        Log.d("num","num :" +num);
        Log.d("total","total :" +total);
        Log.d("price","price :" +price);

        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setProductId(productId);
        cart.setQuantity(num);
        cart.setPrice(price);
        cart.setTotalOder(total);
        Log.d("cart", "addToCart: " + cart.getUserId());
        Log.d("cart", "addToCart: " + cart.getProductId());
        Log.d("cart", "addToCart: " + cart.getPrice());
        Log.d("cart", "addToCart: " + cart.getQuantity());
        Log.d("cart", "addToCart: " + cart.getTotalOder());
        ApiService.api.addToCart(cart).enqueue(new Callback<Call>() {
            @Override
            public void onResponse(Call<Call> call, Response<Call> response) {
                if(response.isSuccessful()){
                    Toast.makeText(DetailActivity.this, "ok", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(DetailActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Call> call, Throwable t) {
                Toast.makeText(DetailActivity.this, "ngu", Toast.LENGTH_SHORT).show();
            }
        });
    }
}