package com.example.mymarket;


import com.example.mymarket.model.User;

import java.util.List;

public interface UserCallback {

    void onCustomerDataReceived(List<User> users);
    void onCustomerDataFailed(Throwable t);
}
