package com.example.studenthome1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@DiscriminatorValue("CHAMBRE")
@AllArgsConstructor
@NoArgsConstructor
public class Chambre extends Logement {

    private int numéroChambre;
    private String typeChambre;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Lit> lits;
}