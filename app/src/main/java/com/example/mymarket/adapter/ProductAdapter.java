package com.example.mymarket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymarket.ProductFragment;
import com.example.mymarket.R;
import com.example.mymarket.model.Prodotto;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Prodotto> listaProdotti;
    private FragmentManager fragmentManager;

    private Context context;

    public ProductAdapter(List<Prodotto> listaProdotti, FragmentManager fragmentManager, Context context) {
        this.listaProdotti = listaProdotti;
        this.fragmentManager = fragmentManager;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card, parent, false);
        return new ProductAdapter.ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {
        String bgUrl ="";
        Prodotto c = listaProdotti.get(position);
        holder.nomeProdotto.setText(c.getNome());
        holder.categoriaProdotto.setText(c.getTipologia());
        holder.origineProdotto.setText(c.getOrigine());

    }

    @Override
    public int getItemCount() {
        return listaProdotti.size();
    }
    static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView categoriaProdotto;
        TextView nomeProdotto;
        TextView origineProdotto;
        TextView quantitaProdotto;
        TextView prezzoProdotto;

        ImageView bgImg;
        //CardView card;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            categoriaProdotto = itemView.findViewById(R.id.product_category);
            nomeProdotto = itemView.findViewById(R.id.product_title);
            origineProdotto = itemView.findViewById(R.id.product_origin);
            quantitaProdotto = itemView.findViewById(R.id.product_qty);
            prezzoProdotto = itemView.findViewById(R.id.product_price);
            bgImg = itemView.findViewById(R.id.bgProduct);
            //card = itemView.findViewById(R.id.category_card);
        }
    }
}
