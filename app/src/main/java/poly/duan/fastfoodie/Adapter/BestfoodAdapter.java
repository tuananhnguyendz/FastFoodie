package poly.duan.fastfoodie.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import poly.duan.fastfoodie.Model.Product;
import poly.duan.fastfoodie.R;

public class BestfoodAdapter extends RecyclerView.Adapter<BestfoodAdapter.viewHolder> {
    List<Product> list_food;
    Context context;

    public BestfoodAdapter(List<Product> list_food) {
        this.list_food = list_food;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
//        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
//        View view = inflater.inflate(R.layout.item_best_food,null);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_best_food,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.txt_productName.setText(list_food.get(position).getProductname());
        holder.txt_description.setText(list_food.get(position).getDescription());
        holder.txt_price.setText("$"+String.valueOf(list_food.get(position).getPrice()));


    }

    @Override
    public int getItemCount() {
        return list_food.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView txt_productName,txt_description,txt_price;
        ImageView img_food;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txt_productName = itemView.findViewById(R.id.txt_productName);
            txt_description = itemView.findViewById(R.id.txt_description);
            txt_price = itemView.findViewById(R.id.txt_price);
            img_food = itemView.findViewById(R.id.img_food);

        }
    }
}
