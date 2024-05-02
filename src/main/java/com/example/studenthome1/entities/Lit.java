package com.example.studenthome1.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@DiscriminatorValue("LIT")
public class Lit extends Logement{
    private int numéroLit;
    private String typeLit;
    private String dimensions;
}
