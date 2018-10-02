package inclass2.group1.beaconproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DiscountsAdapter extends RecyclerView.Adapter<DiscountsAdapter.ViewHolder> {

    Discounts discount;
    ArrayList<Discounts> discountList;
    private Context context;

    public DiscountsAdapter(Context c ,ArrayList<Discounts> discountArrayList) {
        Log.d("test", "Inside Adapter: ");
        this.context = c;
        this.discountList = discountArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.discount_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d("test", "onBindViewHolder: " + discountList.size());
        discount = discountList.get(position);
        Log.d("test", "item on the list : " + discount.toString());
        holder.discountsData = discount;
        holder.discount.setText(discount.getDiscount());
        holder.price.setText(discount.getPrice());
        holder.product.setText(discount.getName());
        holder.region.setText(discount.getRegion());
        Picasso.with(context).load(discount.getPhoto()).centerCrop().into(holder.productImage);
    }

    @Override
    public int getItemCount() {
        return discountList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView product,discount,price,region;
        ImageView productImage;
        Discounts discountsData;

        public ViewHolder(final View itemView) {
            super(itemView);
            this.discountsData = discountsData;
            product = itemView.findViewById(R.id.textProductName);
            discount = itemView.findViewById(R.id.textDiscount);
            price = itemView.findViewById(R.id.textPrice);
            region = itemView.findViewById(R.id.textRegion);
            productImage = itemView.findViewById(R.id.imageProduct);
        }
    }
}
