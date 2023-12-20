package com.example.mymarket;

import com.example.mymarket.model.Store;
import com.example.mymarket.model.User;

import java.util.List;

public interface StoreCallBack {
    void onStoresDataReceived(List<Store> stores);
    void onStoresDataFailed(Throwable t);
}
