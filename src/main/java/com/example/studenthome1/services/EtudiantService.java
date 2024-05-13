package com.example.studenthome1.services;

import com.example.studenthome1.entities.Etudiant;
import com.example.studenthome1.entities.Logement;

import java.util.Date;
import java.util.List;

public interface EtudiantService {


    void AjouterEtudiant(String nom, String prenom, String email, String numeroTel, String dateNaissance, String universite, String password);

    Etudiant getEtudiantById(Long id);

    Etudiant getEtudiantByEmail(String email);

    int deleteEtudiant(Long id);

    List<Etudiant> getAllEtudiants();


    Long getIdByEmail(String email);

}
