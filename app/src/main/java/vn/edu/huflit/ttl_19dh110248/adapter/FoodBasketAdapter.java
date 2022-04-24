package vn.edu.huflit.ttl_19dh110248.adapter;

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

import vn.edu.huflit.ttl_19dh110248.R;
import vn.edu.huflit.ttl_19dh110248.models.FoodBasket;

public class FoodBasketAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    public interface OnFoodBasketItemClickListener {
        void onFoodBasketItemListener(FoodBasket foodBasket);
    }
    ArrayList<FoodBasket> baskets;
    public FoodBasketAdapter(ArrayList<FoodBasket> baskets )
    {
        this.baskets = baskets;
    }
    public class ViewHolderFoodBasket extends RecyclerView.ViewHolder {
        TextView nameFood,qty,priceFood,intoMoney;
        ImageView ivImage;

        public ViewHolderFoodBasket(View itemView) {
            super(itemView);
            nameFood = itemView.findViewById(R.id.nameFood);
            qty = itemView.findViewById(R.id.qty);
            priceFood = itemView.findViewById(R.id.priceFood);
            intoMoney = itemView.findViewById(R.id.intoMoney);
        }
    }

    private List<FoodBasket> foodBaskets;
    private OnFoodBasketItemClickListener onFoodBasketItemClickListener;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_basket_food, parent, false);
        return new ViewHolderFoodBasket(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        FoodBasket foodBasket = foodBaskets.get(position);
        FoodBasketAdapter.ViewHolderFoodBasket viewHolderFoodBasket = (FoodBasketAdapter.ViewHolderFoodBasket) holder;
        viewHolderFoodBasket.nameFood.setText(foodBasket.getName());
        viewHolderFoodBasket.qty.setText(foodBasket.getQuantity());
        viewHolderFoodBasket.priceFood.setText(foodBasket.getPrice());
        int qty = foodBasket.getQuantity();
        int price = foodBasket.getPrice();
        int priceFood = qty * price;
        viewHolderFoodBasket.intoMoney.setText(priceFood);
    }

    @Override
    public int getItemCount() {
        return foodBaskets.size();
    }
}