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
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Service {

    @GET("/clienti/all")
    Call<List<User>> getUsers(
    );
    @GET("/marchio/all")
    Call<List<Brand>> getBrands(
    );
    @GET("/marchio/one/{id}")
    Call<Brand> getSingleBrand(
            @Path("id") int id
    );
    @GET("/marchio/{id}/punti_vendita")
    Call<List<Store>> getPuntiVenditaToMarchio(
            @Path("id") int id_marchio
    );
    @GET("/punto_vendita/one/{id}")
    Call<Store> getSingleStore(
            @Path("id") int id
    );
    @GET("/punto_vendita/all")
    Call<List<Store>> getAllStores(
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
    @DELETE("/deletePuntoVenditaFromMarchio/{id}")
    Call<Void> deleteStore(
            @Path("id") int id
    );
    @POST("/marchio/add")
    Call<Void> createBrand(
            @Body Brand requestData
    );
    @POST("/punto_vendita/addPuntoVenditaToMarchio/{id_brand}")
    Call<Void> createStore(
            @Path("id_brand") int ind_brand,
            @Body Store requestData
    );
    @PUT("/punto_vendita/updatePuntoVendita/{id}")
    Call<Void> updateStore(
            @Path("id") int id,
            @Body Store requestData
    );
    @PUT("/marchio/update/{id}")
    Call<Void> editBrand(
            @Path("id") int id,
            @Body Brand requestData
    );
}