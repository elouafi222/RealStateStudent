package com.example.studenthome1.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "logement_id")
    private Logement logement;

    @ManyToOne
    @JoinColumn(name = "etudiant_id")
    private Etudiant etudiant;

    private Date dateDébut;
    private Date dateFin;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Paiement> paiements;
}

