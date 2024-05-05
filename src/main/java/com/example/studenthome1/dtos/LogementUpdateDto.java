package com.example.studenthome1.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogementUpdateDto {
    private float superficie;
    private String Adresse;
    private String Description;
    private float prix;

    private boolean Disponible;
    private String villeNon;
    private String codePostal;
    private int nbrDechambre;
    private int nbrlit;
}
