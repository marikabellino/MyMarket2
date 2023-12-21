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

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymarket.AddBrandFragment;
import com.example.mymarket.R;
import com.example.mymarket.RetrofitInstance;
import com.example.mymarket.data.StoresList;
import com.example.mymarket.model.Brand;
import com.example.mymarket.model.Store;
import com.example.mymarket.ui.login.AddStoreFragment;

import java.util.List;

public class StoresAdapter extends RecyclerView.Adapter<StoresAdapter.StoresViewHolder> {
    private List<Store> storeList;
    private Context context;
    private int id_brand;

    public StoresAdapter(List<Store> storeList, Context context, FragmentManager fragmentManager, int id_brand) {
        this.storeList = storeList;
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.id_brand = id_brand;
    }

    private FragmentManager fragmentManager;

    @NonNull
    @Override
    public StoresAdapter.StoresViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_store2, parent, false);
        return new StoresAdapter.StoresViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoresAdapter.StoresViewHolder holder, int position) {
        Log.e("giov","sono adapter");
        Store store = storeList.get(position);
        holder.brandName.setText(store.getIndirizzo());
        holder.civico.setText(store.getCivico());
        holder.citta.setText(store.getCitta());
        holder.cap.setText(store.getCAP());

        /*holder.addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("brand", store.getId());
                AddBrandFragment addBrandFragment = new AddBrandFragment();
                addBrandFragment.setArguments(bundle);
                FragmentTransaction fm = fragmentManager.beginTransaction();
                fm.replace(R.id.fragment_container, addBrandFragment);
                fm.addToBackStack(null);
                fm.commit();
            }
        });*/

         /*holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("deleteBtn", " " + store.getId());
                RetrofitInstance retrofitInstance= new RetrofitInstance();
                retrofitInstance.deleteStore(store.getId());

                int position = holder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    removeItem(position);
                }
            }
        });*/

        /*holder.updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putInt("updatedStoreId", store.getId());
                b.putInt("brand_id",id_brand);

                AddStoreFragment addStoreFragment = new AddStoreFragment();
                addStoreFragment.setArguments(b);
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.fragment_container, addStoreFragment );
                ft.addToBackStack(null);
                ft.commit();
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return storeList.size();
    }
    public void removeItem(int position) {
        storeList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
    }

    static class StoresViewHolder extends RecyclerView.ViewHolder {
        TextView brandName;
        TextView indirizzo;
        TextView civico;
        TextView citta;
        TextView cap;
        //ImageButton updatebtn;
        //Button deleteBtn;

        public StoresViewHolder(@NonNull View itemView) {
            super(itemView);
            brandName = itemView.findViewById(R.id.card_title3);
            indirizzo = itemView.findViewById(R.id.indirizzo3);
            civico = itemView.findViewById(R.id.civico3);
            citta = itemView.findViewById(R.id.citta3);
            cap = itemView.findViewById(R.id.cap3);
            //deleteBtn = itemView.findViewById(R.id.deletStoreBtn);
            //updatebtn = itemView.findViewById(R.id.editStoreBtn);
        }
    }
}