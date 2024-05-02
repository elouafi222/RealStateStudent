package com.example.studenthome1.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public  class Logement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private float superficie;
    private String Adresse;
    private String Description;
    private float prix;
    private boolean disponible;

    @ManyToOne
    @JoinColumn(name = "ville_id")
    private Ville ville;

    @OneToMany(mappedBy = "logement", cascade = CascadeType.ALL)
    private List<Commentaire> commentaires;

    private Float noteGlobale;

    //private List<String> listphoto;

    @OneToMany(mappedBy = "logement", cascade = CascadeType.ALL)
    private List<Location> locations;

    @JsonIgnore
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

}
