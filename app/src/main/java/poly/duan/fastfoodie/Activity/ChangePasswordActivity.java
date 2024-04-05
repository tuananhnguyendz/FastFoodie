package poly.duan.fastfoodie.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import poly.duan.fastfoodie.Model.Password;
import poly.duan.fastfoodie.Service.ApiService;
import poly.duan.fastfoodie.databinding.ActivityChangePasswordBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity {
    ActivityChangePasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityChangePasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnDoimk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldpassword =   binding.edtOldPass.getText().toString();
                String newpass =   binding.edtNewPass.getText().toString();
                String confimpass =  binding.edtRePass.getText().toString();

                SharedPreferences sharedPreferences = getSharedPreferences("myPre", MODE_PRIVATE);
                String email = sharedPreferences.getString("mail", "-1");
                Log.d("ngocdv", "zzzz  "+ email);

                Password password = new Password();
                password.setEmail(email);
                password.setPasword(oldpassword);
                password.setNewpassword(newpass);
                password.setConfirmpassword(confimpass);

                ApiService.api.changepass(password).enqueue(new Callback<Password>() {
                    @Override
                    public void onResponse(Call<Password> call, Response<Password> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(ChangePasswordActivity.this, "Thành công", Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(ChangePasswordActivity.this, "Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Password> call, Throwable t) {
                        Log.d("loi", "onFailure: "+t.getMessage());
                    }
                });
            }
        });
    }
}