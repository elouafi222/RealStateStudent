package com.example.studenthome1.services.impl;

import com.example.studenthome1.entities.Logement;
import com.example.studenthome1.entities.Proprietaire;
import com.example.studenthome1.repositories.LogementRepository;
import com.example.studenthome1.repositories.ProprietaireRepository;
import com.example.studenthome1.services.ProprietaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProprietaireServiceImpl implements ProprietaireService {


    private final ProprietaireRepository proprietaireRepository;

    private final LogementRepository logementRepository;

    public ProprietaireServiceImpl(ProprietaireRepository proprietaireRepository,LogementRepository logementRepository){
        this.proprietaireRepository=proprietaireRepository;
        this.logementRepository=logementRepository;
    }

    @Override
    public void ajouterpropiretre(String nom, String prenom,String email, String adresse, String numeroTel, String typePropritaire,String password, List<Logement> logements) {
        Proprietaire objcreer = new Proprietaire(nom,prenom, email,adresse,numeroTel, typePropritaire,password,null);
        proprietaireRepository.save(objcreer);
    }

    @Override
    public Proprietaire getById(Long id) {
        if(id!=-1)
         return proprietaireRepository.findById(id).orElse(null);
        return null;
    }

    @Override
    public Proprietaire getByEmail(String email) {
        return getById(getidByemail(email));
    }

    @Override
    public int deleteProprietere(Long id) {

        Proprietaire proprietaire =proprietaireRepository.findById(id).orElse(null);
        if(proprietaire!=null){
            proprietaireRepository.delete(proprietaire);
            return 1;
        }
        return 0;
    }

    @Override
    public List<Proprietaire> afficherAllProprietere() {
        return proprietaireRepository.findAll();
    }



    @Override
    public List<Logement> getAllLogementbyid(Long id) {
        Proprietaire proprietaire= proprietaireRepository.findById(id).orElse(null);
        if(proprietaire !=null)
             return proprietaire.getLogements();
        return null;
    }

    @Override
    public List<Logement> getAllLogementbyEmail(String email) {
        List<Proprietaire> proprietaire= proprietaireRepository.findAll();
        Proprietaire pro=  proprietaire.stream().filter(val->val.getEmail().equals(email)).findFirst().orElse(null);
        if(pro !=null)
            return pro.getLogements();
        return null;
    }

    @Override
    public void ajouterLogement(Long id, Logement logement) {

        Proprietaire proprietaire= proprietaireRepository.findById(id).orElse(null);

        if(proprietaire!=null){
            logement.setProprietaire(proprietaire);

            proprietaire.getLogements().add(logement);
            logementRepository.save(logement);

            proprietaireRepository.save(proprietaire);


        }
    }

    @Override
    public long getidByemail(String email) {
        if(!email.isEmpty()){
            List<Proprietaire> proprietaire= proprietaireRepository.findAll();
            Proprietaire pro=  proprietaire.stream().filter( val-> val.getEmail() != null && val.getEmail().equals(email)).findFirst().orElse(null);
            if(pro!=null)
                return pro.getId();
        }

        return -1;
    }


}
