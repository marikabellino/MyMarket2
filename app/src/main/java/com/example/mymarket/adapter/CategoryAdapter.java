package com.example.mymarket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymarket.CategorieFragment;
import com.example.mymarket.ProductFragment;
import com.example.mymarket.R;
import com.example.mymarket.model.Brand;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private  String listaCategorie[];
    private FragmentManager fragmentManager;

  private Context context;

    public CategoryAdapter(String[] listaCategorie, FragmentManager fragmentManager, Context context) {
        this.listaCategorie = listaCategorie;
        this.fragmentManager = fragmentManager;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categoria_prodotto, parent, false);
        return new CategoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CategoryViewHolder holder, int position) {
        String bgUrl ="";
        String c = listaCategorie[position];
        holder.title.setText(c);
        switch (c){
            case "Pescheria e macelleria" : holder.bgImg.setImageResource(R.drawable.pescheriaemacelleeria);
            break;
            case "Bevande" : holder.bgImg.setImageResource(R.drawable.frutta);
            break;
            case "Igiene" : holder.bgImg.setImageResource(R.drawable.igiene);
            break;
            case "Panificio" : holder.bgImg.setImageResource(R.drawable.panificio);
            break;
            case "Elettronica" : holder.bgImg.setImageResource(R.drawable.elettronica);
            break;
        }

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductFragment productFragment = new ProductFragment();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.fragment_container, productFragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaCategorie.length;
    }
    static class CategoryViewHolder extends RecyclerView.ViewHolder {
            TextView title;
            ImageView bgImg;
            CardView card;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.product_title_card);
            bgImg = itemView.findViewById(R.id.bgProduct);
            card = itemView.findViewById(R.id.category_card);
        }
    }
}
