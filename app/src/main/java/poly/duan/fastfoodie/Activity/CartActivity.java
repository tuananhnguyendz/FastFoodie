package poly.duan.fastfoodie.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import poly.duan.fastfoodie.R;
import poly.duan.fastfoodie.databinding.ActivityCartBinding;

public class CartActivity extends AppCompatActivity {
    ActivityCartBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnBackCart.setOnClickListener(v -> {
            finish();
        });


    }
}