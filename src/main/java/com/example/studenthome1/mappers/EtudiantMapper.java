package com.example.studenthome1.mappers;

import com.example.studenthome1.dtos.EtudiantDTO;
import com.example.studenthome1.entities.Etudiant;

import java.util.Date;

public class EtudiantMapper {

    public static EtudiantDTO mapToEtudiantDTO(Etudiant etudiant) {

        //String mail=etudiant.getAdresseMail();
        return new EtudiantDTO(
                (int) etudiant.getId(),
                etudiant.getNom(),
                etudiant.getPrenom(),
                etudiant.getAdresseMail(),
                etudiant.getNumeroTel(),
                etudiant.getDateNaissance(),
                etudiant.getUniversite()
        );

    }

    public static Etudiant mapToEtudiant(EtudiantDTO etudiantDTO) {
        return new Etudiant(
                etudiantDTO.getId(),
                etudiantDTO.getNom(),
                etudiantDTO.getPrenom(),
                etudiantDTO.getAdresseMail(),
                etudiantDTO.getNumeroTel(),
                etudiantDTO.getDateNaissance(),
                etudiantDTO.getUniversite()
        );
    }
}

