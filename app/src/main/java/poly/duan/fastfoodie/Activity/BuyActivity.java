package poly.duan.fastfoodie.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import poly.duan.fastfoodie.Model.Address;
import poly.duan.fastfoodie.Model.Product;
import poly.duan.fastfoodie.R;
import poly.duan.fastfoodie.Service.ApiService;
import poly.duan.fastfoodie.databinding.ActivityBuyBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuyActivity extends AppCompatActivity {

    ActivityBuyBinding binding;
    ArrayAdapter<String> spinnerAdapter;
    String[] paymentMethod = {"Tiền mặt", "Thẻ tín dụng", "MoMo"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBuyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        Product product = (Product) getIntent().getSerializableExtra("productId");
//        binding.txtNameOrder.setText(product.getProductname());
////        binding.txtQuantityOrder.setText(product.get());


        spinnerAdapter = new ArrayAdapter<>(this, R.layout.sp_item, paymentMethod);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerPayment.setAdapter(spinnerAdapter);


        binding.spinnerPayment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ///xử lí click chọn thanh toán

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.spinnerLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ///xử lí click chọn địa chỉ
                getAddressUser();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void getAddressUser() {
        SharedPreferences sharedPreferences = getSharedPreferences("myPre", MODE_PRIVATE);
        String userId = sharedPreferences.getString("userId", "-1");
        Log.d("ID nè", "ở Address " + userId);
        Address address = new Address();
        address.setUserId(userId);

        ApiService.api.getAddress(address).enqueue(new Callback<Address>() {
            @Override
            public void onResponse(Call<Address> call, Response<Address> response) {
                if (response.isSuccessful()){
                    Address a = response.body();
                    Log.d("Address", "onResponse:"+a.getAddress());
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(BuyActivity.this,R.layout.sp_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.spinnerLocation.setAdapter(adapter);

                    Toast.makeText(BuyActivity.this, "OK", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(BuyActivity.this, "OK"+response.errorBody(), Toast.LENGTH_SHORT).show();
                    Log.d("ERROR222222", "onResponse: "+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Address> call, Throwable t) {
                Toast.makeText(BuyActivity.this, "SAI"+t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("ERROR333333", "onResponse: "+t.getMessage());

            }
        });


    }


}