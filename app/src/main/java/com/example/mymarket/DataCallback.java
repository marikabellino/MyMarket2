package com.example.mymarket;

import com.example.mymarket.model.Brand;

import java.util.List;

public interface DataCallback {
    void onDataReady(List<Brand> l);
    void onCustomerDataFailed(Throwable t);

}
