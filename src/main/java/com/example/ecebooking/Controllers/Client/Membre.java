package com.example.ecebooking.Controllers.Client;

public class Membre extends Client{
    private String id;
    private String mdp;
    private int numero;

    public Membre(String nom,int numero,String id,String mdp) {
        super(nom);
        this.id = id;
        this.mdp = mdp;
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Membre{" +
                "id='" + id + '\'' +
                ", mdp='" + mdp + '\'' +
                ", numero=" + numero +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
