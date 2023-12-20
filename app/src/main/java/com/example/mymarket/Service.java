package com.example.mymarket;

import com.example.mymarket.model.Brand;
import com.example.mymarket.model.Store;
import com.example.mymarket.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Service {

    @GET("/clienti/all")
    Call<List<User>> getUsers(
    );
    @GET("/marchio/all")
    Call<List<Brand>> getBrands(
    );
    @GET("/marchio/{id}/punti_vendita")
    Call<List<Store>> getPuntiVenditaToMarchio(
            @Path("id") int id_marchio
    );

    @GET("/clienti/one/{email}")
    Call<User> singleUser(
    );
    @POST("/clienti/add")
    Call<User> createUser(
            @Body User requestData
    );

    @DELETE("/clienti/delete/{email}")
    Call<Void> deleteUser(
            @Query("email") String email
    );
    @DELETE("/marchio/delete/{id}")
    Call<Void> deleteBrand(
            @Path("id") int id
    );
    @POST("/marchio/add")
    Call<Brand> createBrand(
            @Body Brand requestData
    );
    @POST("/punto_vendita/addPuntoVenditaToMarchio/{id_brand}")
    Call<Void> createStore(
            @Path("id_brand") int ind_brand,
            @Body Store requestData
    );
}
