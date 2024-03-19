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

import poly.duan.fastfoodie.Model.Food;
import poly.duan.fastfoodie.R;

public class BestfoodAdapter extends RecyclerView.Adapter<BestfoodAdapter.viewHolder> {
    ArrayList<Food> list_food;
    Context context;

    public BestfoodAdapter(ArrayList<Food> list_food) {
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
        holder.txt_title.setText(list_food.get(position).getTitle());
        holder.txt_price.setText("$"+String.valueOf(list_food.get(position).getPrice()));


//        Glide.with(context).load(list_food.get(position).getImagePath()).transform(new CenterCrop(),new RoundedCorners(30)).into(holder.img_food);
//        holder.itemView.setOnClickListener(v -> {
//            Intent intent = new Intent(context, DetailActivity.class);
//            intent.putExtra("object", list_food.get(position));
//            context.startActivity(intent);
//        });

    }

    @Override
    public int getItemCount() {
        return list_food.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView txt_title,txt_time,txt_price,txt_star;
        ImageView img_food;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txt_title = itemView.findViewById(R.id.txt_title);
            txt_price = itemView.findViewById(R.id.txt_price);
            img_food = itemView.findViewById(R.id.img_food);
        }
    }
}
