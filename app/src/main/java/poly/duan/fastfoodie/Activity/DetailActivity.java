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
import poly.duan.fastfoodie.R;
import poly.duan.fastfoodie.Service.ProductService;
import poly.duan.fastfoodie.databinding.ActivityDetailBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
//    private Product product01;
    private int num = 1;

    ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Product product = (Product) getIntent().getSerializableExtra("productId");

//        Log.d("DetailActivity", "ID sản phẩm: " + product.getId()); // Thêm log này để kiểm tra tên ID sản phẩm
        binding.txtProductnameDetail.setText(product.getProductname());
        binding.txtPriceDetail.setText("$"+String.valueOf(product.getPrice()));
        binding.txtDescriptionDetail.setText(product.getDescription());

        binding.txtTotal.setText(String.valueOf(product.getPrice()));


        binding.imgBackDetail.setOnClickListener(v -> {
        finish();
        });

        binding.btnAddtocart.setOnClickListener(v -> {
            //thêm vào giỏ hàng
        });



        binding.plusBtn.setOnClickListener(v -> {
            num = num + 1;
            binding.txtNumTotal.setText(num + "");
            binding.txtTotal.setText("$" + (num * product.getPrice()));
        });

        binding.minBtn.setOnClickListener(v -> {
            if (num > 1) {
                num = num - 1;
                binding.txtNumTotal.setText(num + "");
                binding.txtTotal.setText("$" + (num * product.getPrice()));
            }
        });
        
        binding.btnAddtocart.setOnClickListener(v -> {
        });
    }


    // Phương thức để tìm ID tài nguyên ảnh dựa trên chuỗi ID

}