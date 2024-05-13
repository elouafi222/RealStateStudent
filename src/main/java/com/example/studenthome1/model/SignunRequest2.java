package com.example.studenthome1.model;

import java.util.Date;

public class SignunRequest2 {
    private String nom;
    private String prenom;
    private String adresseMail;
    private String password;
    private String numeroTel;

    private String dateNaissance;
    private String universite;

    public SignunRequest2(String nom, String prenom, String email, String password, String numeroTel) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresseMail = email;
        this.password = password;
        this.numeroTel = numeroTel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresseMail() {
        return adresseMail;
    }

    public void setAdresseMail(String adresseMail) {
        this.adresseMail = adresseMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumeroTel() {
        return numeroTel;
    }

    public void setNumeroTel(String numeroTel) {
        this.numeroTel = numeroTel;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getUniversite() {
        return universite;
    }

    public void setUniversite(String universite) {
        this.universite = universite;
    }
}
