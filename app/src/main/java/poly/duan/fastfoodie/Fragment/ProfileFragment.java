package poly.duan.fastfoodie.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import poly.duan.fastfoodie.Activity.ChangePasswordActivity;
import poly.duan.fastfoodie.R;
import poly.duan.fastfoodie.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {
    FragmentProfileBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater,container,false);

        binding.btnEditDoimk.setOnClickListener(v ->{
            startActivity(new Intent(getContext(), ChangePasswordActivity.class));
        });
        return binding.getRoot();
    }
}