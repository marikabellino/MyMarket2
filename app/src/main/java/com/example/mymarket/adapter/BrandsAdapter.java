package com.example.mymarket.adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymarket.AddBrandFragment;
import com.example.mymarket.R;
import com.example.mymarket.RetrofitInstance;
import com.example.mymarket.data.StoresList;
import com.example.mymarket.model.Brand;
import com.example.mymarket.ui.login.AddStoreFragment;

import java.util.List;

public class BrandsAdapter extends RecyclerView.Adapter<BrandsAdapter.BrandsViewHolder> {
    private List<Brand> brandList;

    private String visibility;
    private Context context;

    public BrandsAdapter(List<Brand> brandList, Context context, FragmentManager fragmentManager, String visibility) {
        this.brandList = brandList;
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.visibility = visibility;

    }

    private FragmentManager fragmentManager;


    @NonNull
    @Override
    public BrandsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_marchio, parent, false);
        return new BrandsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BrandsViewHolder holder, int position) {
        Log.e("giov","sono adapter");
        Brand brand = brandList.get(position);
        Log.e("ciao", visibility);
        holder.brandName.setText(brand.getBrandName());

        if(visibility.equalsIgnoreCase("invisibile")) {

            holder.addbtn.setVisibility(View.INVISIBLE);
            holder.deleteBtn.setVisibility(View.INVISIBLE);
            holder.updateBtn.setVisibility(View.INVISIBLE);
        }


        holder.addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("brand", brand.getId());
                AddStoreFragment addStoreFragment = new AddStoreFragment();
                addStoreFragment.setArguments(bundle);
                FragmentTransaction fm = fragmentManager.beginTransaction();
                fm.replace(R.id.fragment_container, addStoreFragment);
                fm.addToBackStack(null);
                fm.commit();
            }
        });
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("deleteBtn", " "+brand.getId());
                RetrofitInstance retrofitInstance= new RetrofitInstance();
                retrofitInstance.deleteBrand(brand.getId());

                int position = holder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    removeItem(position);
                }
            }
        });
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Bundle b = new Bundle();
                 b.putInt("selectedBrand", brand.getId());
                 Log.e("cliccato", " " + brand.getId());

                 StoresList storesList = new StoresList();
                 storesList.setArguments(b);
                 FragmentTransaction ft = fragmentManager.beginTransaction();
                 ft.replace(R.id.fragment_container, storesList );
                 ft.addToBackStack(null);
                 ft.commit();
            }
        });

        holder.updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("brandbutton","sono cliccato");
                Bundle b = new Bundle();
                b.putInt("selectedBrandino", brand.getId());
                Log.e("cliccato", " " + brand.getId());

                AddBrandFragment addBrandFragment = new AddBrandFragment();
                addBrandFragment.setArguments(b);
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.fragment_container, addBrandFragment );
                ft.addToBackStack(null);
                ft.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return brandList.size();
    }
    public void removeItem(int position) {
        brandList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
    }

    static class BrandsViewHolder extends RecyclerView.ViewHolder {
        TextView brandName;
        CardView card;
        Button addbtn;
        Button deleteBtn;
        ImageButton updateBtn;

        public BrandsViewHolder(@NonNull View itemView) {
            super(itemView);
            brandName = itemView.findViewById(R.id.card_title);
            addbtn = itemView.findViewById(R.id.add_btn);
            deleteBtn = itemView.findViewById(R.id.deleteBrandBtn);
            card = itemView.findViewById(R.id.brand_card);
            updateBtn = itemView.findViewById(R.id.editBrandBtn);
        }
    }
}
