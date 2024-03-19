package poly.duan.fastfoodie.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import poly.duan.fastfoodie.Adapter.BestfoodAdapter;
import poly.duan.fastfoodie.Adapter.CategoryfoodAdapter;
import poly.duan.fastfoodie.Model.Category;
import poly.duan.fastfoodie.Model.Food;
import poly.duan.fastfoodie.Model.Photo;
import poly.duan.fastfoodie.R;
import poly.duan.fastfoodie.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;

    PhotoAdapter adapter;
    BestfoodAdapter foodAdapter;
    CategoryfoodAdapter categoryfoodAdapter;

    private ArrayList<Photo> photoList;
    private ArrayList<Food> foodList;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // Kiểm tra ảnh khi không còn ảnh nào để kiểm tra
            if (binding.viewPager.getCurrentItem() == photoList.size() - 1) {
                binding.viewPager.setCurrentItem(0); // Quay trở lại ảnh ban đầu
            } else {
                binding.viewPager.setCurrentItem(binding.viewPager.getCurrentItem() + 1); // Chuyển sang 1 ảnh tiếp theo
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        photoList = new ArrayList<>();
        photoList.add(new Photo(R.drawable.piza));
        photoList.add(new Photo(R.drawable.burger));
        photoList.add(new Photo(R.drawable.hotdog));
        photoList.add(new Photo(R.drawable.kimchi));

        foodList = list_food();
        adapter = new PhotoAdapter(getContext(), photoList);
        binding.viewPager.setAdapter(adapter);
        binding.circleCenter.setViewPager(binding.viewPager);

        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 3000);
            }
        });
        getBestFood();
        getCategory();
        return binding.getRoot();
    }

    private ArrayList<Food> list_food() {
        ArrayList<Food> foods = new ArrayList<>();
        foods.add(new Food(R.drawable.piza, "Pizza", 200));
        foods.add(new Food(R.drawable.burger, "Burger", 300));
        foods.add(new Food(R.drawable.hotdog, "Hotdog", 400));
        foods.add(new Food(R.drawable.kimchi, "Kimchi", 500));
        return foods;
    }
    private ArrayList<Category> list_category() {
        ArrayList<Category> categories = new ArrayList<>();
        categories.add(new Category(R.drawable.btn_1,"Pizza"));
        categories.add(new Category(R.drawable.btn_2,"Pizza"));
        categories.add(new Category(R.drawable.btn_3,"Pizza"));
        categories.add(new Category(R.drawable.btn_4,"Pizza"));
        categories.add(new Category(R.drawable.btn_5,"Pizza"));
        categories.add(new Category(R.drawable.btn_6,"Pizza"));
        categories.add(new Category(R.drawable.btn_7,"Pizza"));
        categories.add(new Category(R.drawable.btn_8,"Pizza"));
        return categories;
    }

    private void getBestFood() {
        binding.recyclerViewBestfood.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        foodAdapter = new BestfoodAdapter(foodList);
        binding.recyclerViewBestfood.setAdapter(foodAdapter);
    }
    private void getCategory(){
        binding.recyclerViewCategory.setLayoutManager(new GridLayoutManager(getContext(),4));
        categoryfoodAdapter = new CategoryfoodAdapter(list_category());
        binding.recyclerViewCategory.setAdapter(categoryfoodAdapter);
    }
    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }
    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 2000);
    }
}