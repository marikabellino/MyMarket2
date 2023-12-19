
package com.example.mymarket;


import com.example.mymarket.model.User;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {
    @SerializedName("useers")
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

}
