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
@Builder
public class Ville {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ou utiliser un code postal comme identifiant
    private String nom;
    private String codePostal;

    @JsonIgnore
    @OneToMany(mappedBy = "ville", cascade = CascadeType.ALL)
    private List<Logement> logements;

    public Ville(String nom, String codePostal, List<Logement> logements) {
        this.nom = nom;
        this.codePostal = codePostal;
        this.logements = logements;
    }

    public Ville(String nom, String codePostal) {
        this.nom = nom;
        this.codePostal = codePostal;
    }

    public void clonerVille(Ville ville){
        this.nom=ville.nom;
        this.codePostal=ville.codePostal;
    }
}
