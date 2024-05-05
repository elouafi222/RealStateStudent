package com.example.studenthome1.mappers;

import com.example.studenthome1.dtos.EtudiantDTO;
import com.example.studenthome1.dtos.ProprietaireDTO;
import com.example.studenthome1.entities.Etudiant;
import com.example.studenthome1.entities.Proprietaire;

public class ProprietaireMapper {

    public static ProprietaireDTO mapToProprietaireDTO(Proprietaire proprietaire) {

        //String mail=etudiant.getAdresseMail();
        return new ProprietaireDTO(
                proprietaire.getNom(),
                proprietaire.getPrenom(),
                proprietaire.getEmail(),
                proprietaire.getAdresse(),
                proprietaire.getNumeroTel(),
                proprietaire.getTypePropritaire()
        );

    }

    public static Proprietaire mapToProprietaire(ProprietaireDTO propriaitraireDTO,String password) {
        return new Proprietaire(
                propriaitraireDTO.getNom(),
                propriaitraireDTO.getPrenon(),
                propriaitraireDTO.getEmail(),
                propriaitraireDTO.getAdresse(),
                propriaitraireDTO.getNumeroTel(),
                propriaitraireDTO.getTypepropiraitre(),
                password,
                null
        );
    }
}
