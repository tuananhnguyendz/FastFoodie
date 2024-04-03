package poly.duan.fastfoodie.Activity;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;

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
//    List<Address> addressList = new ArrayList<>(); // Thêm danh sách địa chỉ


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBuyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        Product product = (Product) getIntent().getSerializableExtra("productId");
//        binding.txtNameOrder.setText(product.getProductname());
////        binding.txtQuantityOrder.setText(product.get());
        getAddressUser();
        Intent intent = getIntent();
        if (intent != null) {
            Product product = (Product) intent.getSerializableExtra("productId");
            int quantity = intent.getIntExtra("quantity", 0);
            int price = intent.getIntExtra("price", 0);


            // Sau khi nhận được dữ liệu, bạn có thể hiển thị nó lên TextView hoặc bất kỳ thành phần nào khác
            // Ví dụ:
            binding.txtNameOrder.setText(product.getProductname());
            binding.txtQuantityOrder.setText(String.valueOf(quantity));
            binding.txtPriceOrder.setText(String.valueOf(price));

        }

        SharedPreferences sharedPreferences_name = this.getSharedPreferences("myPre", MODE_PRIVATE);
        String userName = sharedPreferences_name.getString("userName", ""); // Lấy tên người dùng từ SharedPreferences
        binding.txtNameUser.setText(userName);

        SharedPreferences sharedPreferences = getSharedPreferences("myPre", MODE_PRIVATE);
        int phone = sharedPreferences.getInt("phone", 0); // Lấy số điện thoại từ SharedPreferences
        Log.d("Số điện thoại", "Số điện thoại lấy từ SharedPreferences: " + phone);
        binding.txtPhoneUser.setText(String.valueOf(phone));


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
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.btnAddtoAddress.setOnClickListener(v -> {
            addToAddress();
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
                if (response.isSuccessful()) {

                    Address a = response.body();
                    Log.d("Address", "onResponse: " + a.getAddress());
                    List<String> addressList = a.getAddress(); // tạo list để gán dữ liệu lên adapter
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(BuyActivity.this, R.layout.sp_item, addressList);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.spinnerLocation.setAdapter(adapter);
                    Toast.makeText(BuyActivity.this, "OK", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(BuyActivity.this, "OK" + response.errorBody(), Toast.LENGTH_SHORT).show();
                    Log.d("ERROR222222", "onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Address> call, Throwable t) {
                Toast.makeText(BuyActivity.this, "OK" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("ERROR33333", "onResponse: " + t.getMessage());
            }
        });
    }

    private void addToAddress() {
        TextInputEditText edt_diachi;
        Button btn_themDiachi;

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.bottomsheet_buy, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(BuyActivity.this);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();

        edt_diachi = view.findViewById(R.id.edt_diachi);
        btn_themDiachi = view.findViewById(R.id.btn_address);
    }


}