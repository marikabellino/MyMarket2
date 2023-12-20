package com.example.mymarket.model;

import java.io.Serializable;
import java.util.List;

public class Store implements Serializable {
    private int id,cap;
    private String citta,civico,indirizzo;
    private List<Prodotto> prodotti;
    private Brand brand;
    public void setBrand(Brand brand){
         this.brand = brand;
    }
    public Brand getBrand(){
        return brand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCAP() {
        return cap;
    }

    public void setCAP(int cap) {
        this.cap = cap;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getCivico() {
        return civico;
    }

    public void setCivico(String civico) {
        this.civico = civico;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public List<Prodotto> getProdotti() {
        return prodotti;
    }

    public void setProdotti(List<Prodotto> prodotti) {
        this.prodotti = prodotti;
    }
}
