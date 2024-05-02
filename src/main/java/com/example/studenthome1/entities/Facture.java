package com.example.studenthome1.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float montantTotal;
    private Date dateFacture;
    private String détails;

    @OneToOne
    @JoinColumn(name = "paiement_id")
    private Paiement paiement;
}

