package com.example.studenthome1.entities;

import com.example.studenthome1.dtos.LogmentModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "logement_type")
public  class Logement  implements Comparable<Logement>  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private float superficie;
    private String Adresse;
    private String Description;
    private float prix;
    private boolean disponible;

    private int nbrDechambre;
    private int nbrlit;

    @ManyToOne
    @JoinColumn(name = "ville_id")
    private Ville ville;

    @OneToMany(mappedBy = "logement", cascade = CascadeType.ALL)
    private List<Commentaire> commentaires;

    private Float noteGlobale;



    @OneToMany(mappedBy = "logement", cascade = CascadeType.ALL)
    private List<Location> locations;

    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "proprietaire_id")
    private Proprietaire proprietaire;


    @OneToMany(mappedBy = "logement", cascade = CascadeType.ALL)
    private List<Image> images;




    public Logement(float superficie, String adresse, String description, float prix, boolean disponible, Ville ville, List<Commentaire> commentaires, Float noteGlobale, List<Location> locations, Proprietaire proprietaire,List<Image> images) {
        this.superficie = superficie;
        Adresse = adresse;
        Description = description;
        this.prix = prix;
        this.disponible = disponible;
        this.ville = ville;
        this.commentaires = commentaires;
        this.noteGlobale = noteGlobale;
        this.locations = locations;
        this.proprietaire = proprietaire;
        this.images=images;
        this.nbrDechambre=0;
        this.nbrlit=0;
    }

    public Logement(float superficie, String adresse, String description, float prix, boolean disponible, Ville ville, List<Commentaire> commentaires, Float noteGlobale, List<Location> locations, Proprietaire proprietaire,List<Image> images,int nbrchambre,int nbrlit) {
        this.superficie = superficie;
        Adresse = adresse;
        Description = description;
        this.prix = prix;
        this.disponible = disponible;
        this.ville = ville;
        this.commentaires = commentaires;
        this.noteGlobale = noteGlobale;
        this.locations = locations;
        this.proprietaire = proprietaire;
        this.images=images;
        this.nbrDechambre=nbrchambre;
        this.nbrlit=nbrlit;
    }



    public void calculerNoteGlobale() {
        if (commentaires == null || commentaires.isEmpty()) {
            noteGlobale = 0f; // ou une valeur par défaut
            return;
        }
        int totalNotes = 0;
        for (Commentaire commentaire : commentaires) {
            totalNotes += commentaire.getNote();
        }
        noteGlobale = (float) totalNotes / commentaires.size();
    }

    @Override
    public int compareTo(Logement autreLogement) {
        if (this.id < autreLogement.id) {
            return 1; // L'objet courant est inférieur à l'objet passé en paramètre
        } else if (this.id > autreLogement.id) {
            return -1; // L'objet courant est supérieur à l'objet passé en paramètre
        } else {
            return 0; // Les deux objets sont considérés égaux
        }
    }
}
