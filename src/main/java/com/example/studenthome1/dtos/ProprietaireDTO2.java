package com.example.studenthome1.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProprietaireDTO2 {

    private String nom;
    private String prenon;
    private String adresse;
    private String numeroTel;
}
