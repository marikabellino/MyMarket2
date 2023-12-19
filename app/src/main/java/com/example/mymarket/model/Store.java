package com.example.mymarket.model;

import java.util.List;

public class Store {
    private int id,CAP;
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
        return CAP;
    }

    public void setCAP(int CAP) {
        this.CAP = CAP;
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
