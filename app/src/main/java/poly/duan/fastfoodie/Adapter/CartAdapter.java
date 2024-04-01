package poly.duan.fastfoodie.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import poly.duan.fastfoodie.Model.CartItem;
import poly.duan.fastfoodie.R;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.viewHolder> {
    List<CartItem> list;
    Context context;

    public CartAdapter(List<CartItem> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        CartItem item = list.get(position);
        holder.nameCart.setText(list.get(position).getProductname());
        holder.priceCart.setText(String.valueOf(list.get(position).getPrice()));
        holder.numCart.setText(String.valueOf(list.get(position).getQuantity()));
        holder.totalCart.setText(String.valueOf(list.get(position).getTotal_order()));

//        holder.minCart.setOnClickListener(v -> {
//            if (item.getQuantity() > 1) {
//                item.setQuantity(item.getQuantity() - 1);
//                holder.numCart.setText(String.valueOf(item.getQuantity()));
//
//                double newTotal = item.getPrice() * item.getQuantity();
//                holder.totalCart.setText(String.valueOf(newTotal));
//            }
//        });

        holder.minCart.setOnClickListener(v -> {
            if (item.getQuantity() > 1) {
                item.setQuantity(item.getQuantity() - 1);
                holder.numCart.setText(String.valueOf(item.getQuantity()));

                double newTotal = item.getPrice() * item.getQuantity();
                holder.totalCart.setText(String.valueOf(newTotal));
            } else if (item.getQuantity() == 1) {
                // Giảm số lượng về 0 và ẩn sản phẩm
                item.setQuantity(0);
                holder.numCart.setText(String.valueOf(0));
                holder.totalCart.setText(String.valueOf(0));
                holder.itemView.setVisibility(View.GONE);
            }
        });

        holder.maxCart.setOnClickListener(v -> {
            item.setQuantity(item.getQuantity() + 1);
            holder.numCart.setText(String.valueOf(item.getQuantity()));
            double newTotal = item.getPrice() * item.getQuantity();
            holder.totalCart.setText(String.valueOf(newTotal));
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView img_picCart;
        TextView minCart, maxCart, numCart, totalCart, priceCart, nameCart;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            img_picCart = itemView.findViewById(R.id.img_pic_cart);
            minCart = itemView.findViewById(R.id.min_btn_cart);
            maxCart = itemView.findViewById(R.id.plus_btn_cart);
            numCart = itemView.findViewById(R.id.txt_num_cart);
            totalCart = itemView.findViewById(R.id.txt_toTalCart);
            priceCart = itemView.findViewById(R.id.txt_priceCart);
            nameCart = itemView.findViewById(R.id.txt_title_cart);


        }
    }
}
