package com.example.studenthome1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogementModel {

    private float superficie;
    private String Adresse;
    private String Description;
    private float prix;
    private boolean disponible;

    private String villeNon;
    private String codePostal;

    private List<String> images;

    private int nbrDechambre;
    private int nbrlit;

    public LogementModel(float superficie, String adresse, String description, float prix, boolean disponible, String villeNon, String codePostal,List<String> images) {
        this.superficie = superficie;
        Adresse = adresse;
        Description = description;
        this.prix = prix;
        this.disponible = disponible;
        this.villeNon = villeNon;
        this.codePostal = codePostal;
        this.images=images;
        this.nbrDechambre=0;
        this.nbrlit=0;
    }



    public float getSuperficie() {
        return superficie;
    }

    public void setSuperficie(float superficie) {
        this.superficie = superficie;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getVilleNon() {
        return villeNon;
    }

    public void setVilleNon(String villeNon) {
        this.villeNon = villeNon;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public int getNbrDechambre() {
        return nbrDechambre;
    }

    public void setNbrDechambre(int nbrDechambre) {
        this.nbrDechambre = nbrDechambre;
    }

    public int getNbrlit() {
        return nbrlit;
    }

    public void setNbrlit(int nbrlit) {
        this.nbrlit = nbrlit;
    }
}
