package com.example.mymarket.ui.login;

import com.example.mymarket.model.Prodotto;

import java.util.List;

public interface ProdottoCallback {

    void onProductReceived(List<Prodotto> prodotti);

    void onProductFailure(Throwable t);
}
