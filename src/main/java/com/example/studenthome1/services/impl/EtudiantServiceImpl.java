package com.example.studenthome1.services.impl;

import com.example.studenthome1.entities.Etudiant;
import com.example.studenthome1.repositories.EtudiantRepository;
import com.example.studenthome1.repositories.LogementRepository;
import com.example.studenthome1.services.EtudiantService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EtudiantServiceImpl implements EtudiantService {
    private final EtudiantRepository etudiantRepository;
    private final LogementRepository logementRepository;

    public EtudiantServiceImpl(EtudiantRepository etudiantRepository, LogementRepository logementRepository) {
        this.etudiantRepository = etudiantRepository;
        this.logementRepository = logementRepository;
    }

    @Override
    public void AjouterEtudiant(String nom, String prenom, String email, String numeroTel, String dateNaissance, String universite, String password) {
        Etudiant etudiant = new Etudiant(nom, prenom, email, numeroTel, dateNaissance, universite, password);
        etudiantRepository.save(etudiant);
    }

    @Override
    public Etudiant getEtudiantById(Long id) {
        return etudiantRepository.findById(id).orElse(null);
    }

    @Override
    public Etudiant getEtudiantByEmail(String email) {
        return etudiantRepository.findByAdresseMail(email);
    }

    @Override
    public int deleteEtudiant(Long id) {
        if (etudiantRepository.existsById(id)) {
            etudiantRepository.deleteById(id);
            return 1;
        }
        return 0;
    }

    @Override
    public List<Etudiant> getAllEtudiants() {
        return etudiantRepository.findAll();
    }



    @Override
    public Long getIdByEmail(String email) {
        Etudiant etudiant = etudiantRepository.findByAdresseMail(email);
        return etudiant != null ? etudiant.getId() : -1;
    }
}
