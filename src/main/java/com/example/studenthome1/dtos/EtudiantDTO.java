package com.example.studenthome1.dtos;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EtudiantDTO {
    private long id;
    private String nom;
    private String prenom;
    private String adresseMail;
    private String numeroTel;
    private Date dateNaissance;
    private String universite;

}
