package com.example.mymarket.model;

import java.io.Serializable;
import java.util.List;

public class Brand implements Serializable {
    private int id;
    private String nome;
    private List<Store> puntiVendita;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrandName() {
        return nome;
    }

    public void setBrandName(String nome) {
        this.nome= nome;
    }

    public List<Store> getStoresList() {
        return puntiVendita;
    }

    public void setStoresList(List<Store> storesList) {
        this.puntiVendita = storesList;
    }
}
