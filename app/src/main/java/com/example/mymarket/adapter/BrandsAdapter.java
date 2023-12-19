package com.example.mymarket.adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymarket.R;
import com.example.mymarket.RetrofitInstance;
import com.example.mymarket.model.Brand;
import com.example.mymarket.ui.login.AddStoreFragment;

import java.util.List;

public class BrandsAdapter extends RecyclerView.Adapter<BrandsAdapter.BrandsViewHolder> {
    private List<Brand> brandList;
    private Context context;

    public BrandsAdapter(List<Brand> brandList, Context context, FragmentManager fragmentManager) {
        this.brandList = brandList;
        this.context = context;
        this.fragmentManager = fragmentManager;
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
        holder.brandName.setText(brand.getBrandName());
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
            }
        });
    }

    @Override
    public int getItemCount() {
        return brandList.size();
    }

    static class BrandsViewHolder extends RecyclerView.ViewHolder {
        TextView brandName;

        Button addbtn;
        Button deleteBtn;

        public BrandsViewHolder(@NonNull View itemView) {
            super(itemView);
            brandName = itemView.findViewById(R.id.card_title);
            addbtn = itemView.findViewById(R.id.add_btn);
            deleteBtn = itemView.findViewById(R.id.deleteBrandBtn);
        }
    }
}
