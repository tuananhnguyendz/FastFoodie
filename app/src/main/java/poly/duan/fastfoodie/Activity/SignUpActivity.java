package poly.duan.fastfoodie.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import poly.duan.fastfoodie.Model.User;
import poly.duan.fastfoodie.Service.ApiService;
import poly.duan.fastfoodie.databinding.ActivitySignUpBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.txtLogin.setOnClickListener(v -> {
            startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
        });

        binding.btnSignup.setOnClickListener(v -> {
            getSignUp();
        });
    }
    private void getSignUp() {
        String name = binding.edtNameSignup.getText().toString().trim();
        String email = binding.edtEmailSignup.getText().toString().trim();
        String phoneText = binding.edtPhoneSignup.getText().toString().trim();
        String pass = binding.edtPassSignup.getText().toString().trim();

        // Kiểm tra xem các trường có trống không
        if (name.isEmpty() || email.isEmpty() || phoneText.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        // Kiểm tra xem chuỗi số điện thoại có chứa ký tự không phải số không
        if (!phoneText.matches("\\d+")) {
            Toast.makeText(this, "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
            return;
        }

        // Chuyển đổi số điện thoại từ chuỗi sang kiểu int
        int phone = Integer.parseInt(phoneText);

        // Tiếp tục xử lý đăng ký nếu không có trường nào trống
        User user = new User();
        user.setUsername(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(pass);

        // Gọi API đăng ký
        ApiService.api.register(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(SignUpActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                // Xử lý phản hồi từ API nếu cần
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("API_CALL_FAILURE", "Đăng ký thất bại: " + t.getMessage());
                Toast.makeText(SignUpActivity.this, "Đăng ký thất bại: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}