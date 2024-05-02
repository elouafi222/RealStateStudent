package com.example.studenthome1.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@DiscriminatorValue("APPARTEMENT")
@Data
public class Appartement extends Logement {
    private int numéroAppartement;
    private Integer étage;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Chambre> chambres;
}
