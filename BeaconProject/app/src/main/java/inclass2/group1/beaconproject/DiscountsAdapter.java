package inclass2.group1.beaconproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
        if(discount.getRegion().equals("lifestyle")){
            if(discount.getImage().equals("jelly_beans.png")){
                holder.productImage.setImageResource(R.drawable.jelly_beans);
            }else if(discount.getImage().equals("scotch_brite_sponges.png")){
                holder.productImage.setImageResource(R.drawable.scotch_brite_sponges);
            }else if(discount.getImage().equals("organix_conditioner.png")){
                holder.productImage.setImageResource(R.drawable.organix_conditioner);
            }else{
                holder.productImage.setImageResource(R.drawable.us_weekly);
            }
        }else if(discount.getRegion().equals("produce")){
            if(discount.getImage().equals("pineapple.png")){
                holder.productImage.setImageResource(R.drawable.pineapple);
            }else if(discount.getImage().equals("oranges.png")){
                holder.productImage.setImageResource(R.drawable.oranges);
            }else if(discount.getImage().equals("lettuce.png")){
                holder.productImage.setImageResource(R.drawable.lettuce);
            }else if(discount.getImage().equals("spinach.png")){
                holder.productImage.setImageResource(R.drawable.spinach);
            }else if(discount.getImage().equals("fresh_nectarines.png")){
                holder.productImage.setImageResource(R.drawable.fresh_nectarines);
            }else{
                holder.productImage.setImageResource(R.drawable.watermellon);
            }
        }else{
            if(discount.getImage().equals("croissants.png")){
                holder.productImage.setImageResource(R.drawable.croissants);
            }else if(discount.getImage().equals("coca_cola.png")){
                holder.productImage.setImageResource(R.drawable.coca_cola);
            }else if(discount.getImage().equals("gatorade.png")){
                holder.productImage.setImageResource(R.drawable.gatorade);
            }else if(discount.getImage().equals("cranberry_cocktail.png")){
                holder.productImage.setImageResource(R.drawable.cranberry_cocktail);
            }else if(discount.getImage().equals("milk.jpg")){
                holder.productImage.setImageResource(R.drawable.milk);
            }else if(discount.getImage().equals("hi_c_fruit_punch.png")){
                holder.productImage.setImageResource(R.drawable.hi_c_fruit_punch);
            }else{
                //holder.productImage.set;
            }
        }

        holder.region.setText(discount.getRegion());
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
