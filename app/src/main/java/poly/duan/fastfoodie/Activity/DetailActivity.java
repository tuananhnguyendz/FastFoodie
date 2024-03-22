package poly.duan.fastfoodie.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import poly.duan.fastfoodie.Model.Product;
import poly.duan.fastfoodie.R;
import poly.duan.fastfoodie.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {
    private Product product;
    private int num = 1;

    ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Product product = (Product) getIntent().getSerializableExtra("productId");
        binding.txtProductnameDetail.setText(product.getProductname());
        binding.txtPriceDetail.setText("$"+String.valueOf(product.getPrice()));
        binding.txtDescriptionDetail.setText(product.getDescription());

        binding.imgBackDetail.setOnClickListener(v -> {
        finish();
        });

        binding.plusBtn.setOnClickListener(v -> {
            num = num + 1;
            binding.txtNum.setText(num + "");
            binding.txtTotal.setText("$" + (num * product.getPrice()));
        });

        binding.minBtn.setOnClickListener(v -> {
            if (num > 0) {
                num = num - 1;
                binding.txtNum.setText(num + "");
                binding.txtTotal.setText("$" + (num * product.getPrice()));
            }
        });
    }
}