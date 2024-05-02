package com.example.studenthome1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
    private String prenom;
    private String adresseMail;
    private String password;
    private String numeroTel;
    private Date dateNaissance;
    private String universite;

    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL)
    private List<Location> locations;

    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL)
    private List<Commentaire> commentaires;

    public Etudiant(long id, String nom, String prenom, String adresseMail, String numeroTel, Date dateNaissance, String universite) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresseMail = adresseMail;
        this.numeroTel = numeroTel;
        this.dateNaissance = dateNaissance;
        this.universite = universite;
    }


}