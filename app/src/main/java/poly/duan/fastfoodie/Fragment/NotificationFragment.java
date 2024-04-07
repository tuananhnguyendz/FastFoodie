package poly.duan.fastfoodie.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import poly.duan.fastfoodie.R;


public class NotificationFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification,container,false);
        return view;
    }

    public void disPlayMsg(String msg){
        Toast.makeText(getActivity(), "message", Toast.LENGTH_SHORT).show();
    }
}