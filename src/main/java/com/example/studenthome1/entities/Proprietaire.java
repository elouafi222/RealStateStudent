package com.example.studenthome1.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "proprietaire_type")
public class Proprietaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String adresse;
    private String numeroTel;
    private String typePropritaire;

    public Proprietaire(String nom, String prenom,String email, String adresse, String numeroTel, String typePropritaire,String password, List<Logement> logements) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.numeroTel = numeroTel;
        this.typePropritaire = typePropritaire;
        this.logements = logements;
        this.email=email;
        this.password=password;
    }


    @JsonIgnore
    @OneToMany(mappedBy = "proprietaire", cascade = CascadeType.ALL)
    private List<Logement> logements;
}
