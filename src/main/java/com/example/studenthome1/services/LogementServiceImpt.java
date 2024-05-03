package com.example.studenthome1.services;


import com.example.studenthome1.entities.Logement;
import com.example.studenthome1.repositories.LogementRepository;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public class LogementServiceImpt {

    private final LogementRepository logementRepository;

    private static int nbrafficher=8;

    public LogementServiceImpt(LogementRepository logementRepository){
        this.logementRepository=logementRepository;
    }


    public Logement ajouterNouveuLogment(){
        return null;

    }

    public List<Logement> afficherAlllogementById(Long id){

        return logementRepository.findAll().stream().filter(val->val.getProprietaire().getId()==id).toList();
    }

    public List<Logement> afficherAlllogementByindex(int index){

        if(index>0){

            Pageable pageable = PageRequest.of(index - 1, nbrafficher);
            Page<Logement> logementsPage = logementRepository.findAll(pageable);
            return logementsPage.getContent();
        }
        else
            return null;

    }

    public List<Logement> afficherAlllogmentBySearch(String search){

        int ver=0;
        int entier=0;
        try {
            entier = Integer.parseInt(search);
            ver=1;

        } catch (NumberFormatException e) {
            System.out.println("La chaîne ne peut pas être convertie en entier.");
            ver=0;
        }

        if(ver==1){
            ver=0;
            int finalEntier = entier;
           return logementRepository.findAll().stream().filter(val->val.getNbrDechambre()== finalEntier || val.getNbrlit()== finalEntier).toList();
        }else{
            return logementRepository.findAll().stream().filter(val->val.getAdresse().contains(search) || val.getDescription().contains(search) || val.getVille().getNom().contains(search) || val.getVille().getCodePostal().contains(search)).toList();
        }

    }


}
