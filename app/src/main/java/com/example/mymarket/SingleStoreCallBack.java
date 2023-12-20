package com.example.mymarket;

import com.example.mymarket.model.Store;


import java.util.List;

public interface SingleStoreCallBack {

    void onSingleStoreDataReceived(Store store);
    void onSingleStoreDataFailed(Throwable t);
}
