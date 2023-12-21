package com.example.mymarket.data.interfaces;

import com.example.mymarket.model.User;

public interface SingleUserCallback {

    void onSingleUserDataReceived(User user);
    void onSingleUserDataFailed(Throwable t);
}
