package poly.duan.fastfoodie.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import poly.duan.fastfoodie.Activity.ChangePasswordActivity;
import poly.duan.fastfoodie.Activity.IntroActivity;
import poly.duan.fastfoodie.R;
import poly.duan.fastfoodie.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {
    FragmentProfileBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false);

        binding.txtDoimkProfile.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), ChangePasswordActivity.class));
        });
        binding.txtLogOut.setOnClickListener(v -> {
            getDialogOut();
        });
        return binding.getRoot();
    }

    private void getDialogOut() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setIcon(R.drawable.warning);
        builder.setTitle("Notification");
        builder.setMessage("Do you want to log out?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                LogOut();
                Toast.makeText(getContext(), "Signed out successfully", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void LogOut() {
        startActivity(new Intent(getContext(), IntroActivity.class));
    }
}