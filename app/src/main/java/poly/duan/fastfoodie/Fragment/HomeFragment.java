package poly.duan.fastfoodie.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import poly.duan.fastfoodie.Adapter.BestfoodAdapter;
import poly.duan.fastfoodie.Adapter.CategoryfoodAdapter;
import poly.duan.fastfoodie.Adapter.PhotoAdapter;
import poly.duan.fastfoodie.Model.Category;
import poly.duan.fastfoodie.Model.Photo;
import poly.duan.fastfoodie.Model.Product;
import poly.duan.fastfoodie.R;
import poly.duan.fastfoodie.Service.ProductService;
import poly.duan.fastfoodie.databinding.FragmentHomeBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;

    PhotoAdapter adapter;
    BestfoodAdapter foodAdapter;
    CategoryfoodAdapter categoryfoodAdapter;

    private ArrayList<Photo> photoList;
    //    private ArrayList<Food> foodList;
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

        adapter = new PhotoAdapter(getContext(), photoList);
        binding.viewPager.setAdapter(adapter);
        binding.circleCenter.setViewPager(binding.viewPager);
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),DividerItemDecoration.HORIZONTAL);
//        binding.recyclerViewBestfood.addItemDecoration(dividerItemDecoration);
//        foodList = new ArrayList<>();

        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 3000);
            }
        });
        getBestfood();
        getCategory();
        return binding.getRoot();
    }


    private ArrayList<Category> list_category() {
        ArrayList<Category> categories = new ArrayList<>();
        categories.add(new Category(R.drawable.btn_1, "Pizza"));
        categories.add(new Category(R.drawable.btn_2, "Pizza"));
        categories.add(new Category(R.drawable.btn_3, "Pizza"));
        categories.add(new Category(R.drawable.btn_4, "Pizza"));
        categories.add(new Category(R.drawable.btn_5, "Pizza"));
        categories.add(new Category(R.drawable.btn_6, "Pizza"));
        categories.add(new Category(R.drawable.btn_7, "Pizza"));
        categories.add(new Category(R.drawable.btn_8, "Pizza"));
        return categories;
    }

    private void getBestfood() {
        ProductService.api.getSanPham().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    List<Product> list_product = response.body();
                    if (list_product != null && !list_product.isEmpty()) {
                        for (Product product : list_product) {
                            Log.d("Product", "ProductName" + product.getProductname() + product.getPrice());
                        }
//                        Toast.makeText(getContext(), "Call API thành công", Toast.LENGTH_SHORT).show();
                        foodAdapter = new BestfoodAdapter(list_product);
                        binding.recyclerViewBestfood.setAdapter(foodAdapter);
                        binding.recyclerViewBestfood.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                    }
                } else {
                    // Xử lý khi phản hồi không thành công
                    Log.e("API_CALL_FAILURE", "Không thể nhận dữ liệu sản phẩm từ API: " + response.message());
                    Toast.makeText(getContext(), "Không thể nhận dữ liệu sản phẩm từ API: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("API_CALL_FAILURE", "Không thể nhận dữ liệu sản phẩm từ API: " + t.getMessage());
                Toast.makeText(getContext(), "Call API thất bại", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void getCategory() {
        binding.recyclerViewCategory.setLayoutManager(new GridLayoutManager(getContext(), 4));
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