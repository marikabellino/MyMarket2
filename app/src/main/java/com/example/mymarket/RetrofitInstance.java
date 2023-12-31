
package com.example.mymarket;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymarket.adapter.BrandsAdapter;
import com.example.mymarket.adapter.ProductAdapter;
import com.example.mymarket.adapter.StoresAdapter;
import com.example.mymarket.data.interfaces.SingleUserCallback;
import com.example.mymarket.model.Brand;
import com.example.mymarket.model.Prodotto;
import com.example.mymarket.model.Store;
import com.example.mymarket.model.User;
import com.example.mymarket.ui.login.ProdottoCallback;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private List<User> users;
    private List<Brand> brands;
    private List<Store> stores;
    private List<Prodotto> prodotti;
    private Store singleStore;
    private User user;

    String baseUrl = "https://sacred-nominally-lizard.ngrok-free.app";
    //String baseUrl = "https://2796-151-12-133-222.ngrok-free.app";
   // String baseUrl = "https://0ee6-151-12-133-222.ngrok-free.app";
    //String baseUrl = "https://0bb7-151-12-133-222.ngrok-free.app";

    Retrofit retrofit =
            new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(baseUrl)
                    .build();

    Service retrofitService = retrofit.create(Service.class);

    public void readUsers(UserAdapter userAdapter) {

        Retrofit retrofit =
                new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(baseUrl)
                        .build();
        Service retrofitService = retrofit.create(Service.class);

        Call<List<User>> call = retrofitService.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call,
                                   Response<List<User>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    users = response.body();
                    //customerAdapter.notifyDataSetChanged();
                    //callback.onCustomerDataReceived(customers);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {
                Log.e("failure", "failure: " + t);
                users = null;
                //callback.onUserDataFailed(t);
            }
        });
    }
    public void readBrands(BrandsAdapter brandsAdapter, DataCallback callback) {
        Retrofit retrofit =
                new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(baseUrl)
                        .build();
        Service retrofitService = retrofit.create(Service.class);
        Call<List<Brand>> call = retrofitService.getBrands();
        call.enqueue(new Callback<List<Brand>>() {
            @Override
            public void onResponse(Call<List<Brand>> call,
                                   Response<List<Brand>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    brands = response.body();
                    Log.e("giov",brands.toString());
                    brandsAdapter.notifyDataSetChanged();
                    if(brands != null)
                    {
                        callback.onDataReady(brands);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Brand>> call, @NonNull Throwable t) {
                Log.e("failure", "failure: " + t);
                callback.onCustomerDataFailed(t);
            }
        });
    }

    public void singleUser(String email, SingleUserCallback singleUserCallback) {
        //Log.e("isUser", email+"xxx");
        Call<User> call = retrofitService.singleUser(email);
        // Log.e("isUser", call.request().url().toString());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User  user = response.body();
                //Log.e("isUser", user.toString());
                singleUserCallback.onSingleUserDataReceived(user);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("isUser", "errore");
                user = null;
            }
        });
    }

    public void addUser(User user) {
        Retrofit retrofit =
                new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(baseUrl)
                        .build();
        Service retrofitService = retrofit.create(Service.class);
        Call<User> call = retrofitService.createUser(user);
        Log.e("chiamata", call.request().url().toString());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.e("UserAdded", "ciao");
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
            }
        });
    }

    public void addBrand(Brand brand) {
        Log.e("oggettino", "oggetto: " + brand.toString());
        Retrofit retrofit =
                new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(baseUrl)
                        .build();
        Service retrofitService = retrofit.create(Service.class);
        Call<Void> call = retrofitService.createBrand(brand);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.e("oggettino", response.message().toString());
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("myLog", t.getMessage().toString());
            }
        });
    }
    public void addStore(int id_brand, Store s){
        Retrofit retrofit =
                new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(baseUrl)
                        .build();
        Service retrofitService = retrofit.create(Service.class);
        Call<Void> call = retrofitService.createStore(id_brand,s);
        Log.e("chiamatamine", call.request().url().toString());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.e("myLog", "store aggiunto correttamente");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("myLog", t.toString());
            }
        });
    }


    public void deleteBrand(int id) {
        Retrofit retrofit =
                new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(baseUrl)
                        .build();
        Service retrofitService = retrofit.create(Service.class);
        Call<Void> call = retrofitService.deleteBrand(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call,
                                   @NonNull Response<Void> response) {
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {

            }
        });
    }

    public void readStores(StoresAdapter storesAdapter, StoreCallBack callback, int id_brand) {
        Retrofit retrofit =
                new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(baseUrl)
                        .build();
        Service retrofitService = retrofit.create(Service.class);
        Call<List<Store>> call = retrofitService.getPuntiVenditaToMarchio(id_brand);
        call.enqueue(new Callback<List<Store>>() {
            @Override
            public void onResponse(Call<List<Store>> call,
                                   Response<List<Store>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    stores = response.body();
                    Log.e("giov",stores.toString());
                    storesAdapter.notifyDataSetChanged();
                    if(stores != null)
                    {
                        callback.onStoresDataReceived(stores);
                    }

                    //rv.setAdapter(brandsAdapter);
                    // dataCallback.onDataReady(brands);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Store>> call, @NonNull Throwable t) {
                Log.e("failure", "failure: " + t);
                stores = null;
                callback.onStoresDataFailed(t);
            }

        });
    }

    public void deleteStore(int id) {
        Retrofit retrofit =
                new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(baseUrl)
                        .build();
        Service retrofitService = retrofit.create(Service.class);
        Call<Void> call = retrofitService.deleteStore(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call,
                                   @NonNull Response<Void> response) {
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {

            }
        });
    }

    public void updateStore(int id, Store updatedStore) {
        Retrofit retrofit =
                new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(baseUrl)
                        .build();
        Service retrofitService = retrofit.create(Service.class);
        Call<Void> call = retrofitService.updateStore(id, updatedStore);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call,
                                   @NonNull Response<Void> response) {
                if (response.isSuccessful())
                {
                    Log.e("oggetto","ciaone si " + updatedStore.toString());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Log.e("ciao","ciaone no");
            }
        });
    }

    public void singleStore(int id, SingleStoreCallBack singleStoreCallBack) {
        Retrofit retrofit =
                new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(baseUrl)
                        .build();
        Service retrofitService = retrofit.create(Service.class);
        Call<Store> call = retrofitService.getSingleStore(id);
        call.enqueue(new Callback<Store>() {
            @Override
            public void onResponse(@NonNull Call<Store> call,
                                   @NonNull Response<Store> response) {
                if (response.isSuccessful() && response.body() != null) {
                    singleStore = response.body();
                    Log.e("singlestore",singleStore.toString());
                    singleStoreCallBack.onSingleStoreDataReceived(singleStore);
                }
            }
            @Override
            public void onFailure(@NonNull Call<Store> call, @NonNull Throwable t) {

            }
        });
    }

    public void readAllStores(StoresAdapter storesAdapter, StoreCallBack callBack) {
        Retrofit retrofit =
                new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(baseUrl)
                        .build();
        Service retrofitService = retrofit.create(Service.class);
        Call<List<Store>> call = retrofitService.getAllStores();
        call.enqueue(new Callback<List<Store>>() {
            @Override
            public void onResponse(Call<List<Store>> call,
                                   Response<List<Store>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    stores = response.body();
                    //Log.e("giov",stores.toString());
                    storesAdapter.notifyDataSetChanged();
                    if(stores != null)
                    {
                        callBack.onStoresDataReceived(stores);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Store>> call, @NonNull Throwable t) {
                Log.e("failure", "failure: " + t);
                callBack.onStoresDataFailed(t);
            }
        });
    }
    public void readAllProducts(ProductAdapter productAdapter, ProdottoCallback callBack, String tipologia) {
        Retrofit retrofit =
                new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(baseUrl)
                        .build();
        Service retrofitService = retrofit.create(Service.class);
        Call<List<Prodotto>> call = retrofitService.getProducts(tipologia);
        call.enqueue(new Callback<List<Prodotto>>() {
            @Override
            public void onResponse(Call<List<Prodotto>> call,
                                   Response<List<Prodotto>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    prodotti = response.body();
                    //Log.e("giov",stores.toString());
                    productAdapter.notifyDataSetChanged();
                    if(prodotti != null)
                    {
                        callBack.onProductReceived(prodotti);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Prodotto>> call, @NonNull Throwable t) {
                Log.e("failure", "failure: " + t);
                callBack.onProductFailure(t);
            }
        });
    }
}
