package com.example.studenthome1.model;

public class SignUnRequest {

    private String nom;
    private String prenon;
    private String email;

    private String password;

    private String adresse;
    private String numeroTel;
    private String typePropritaire;

    public SignUnRequest() {
    }

    public SignUnRequest(String nom, String prenon, String email, String password, String adresse, String numeroTel, String typePropritaire) {
        this.nom = nom;
        this.prenon = prenon;
        this.email = email;
        this.password = password;
        this.adresse = adresse;
        this.numeroTel = numeroTel;
        this.typePropritaire = typePropritaire;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenon() {
        return prenon;
    }

    public void setPrenon(String prenon) {
        this.prenon = prenon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNumeroTel() {
        return numeroTel;
    }

    public void setNumeroTel(String numeroTel) {
        this.numeroTel = numeroTel;
    }

    public String getTypePropritaire() {
        return typePropritaire;
    }

    public void setTypePropritaire(String typePropritaire) {
        this.typePropritaire = typePropritaire;
    }
}
