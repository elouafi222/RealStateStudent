package com.example.studenthome1.services;


import com.example.studenthome1.entities.Logement;
import com.example.studenthome1.repositories.LogementRepository;
import lombok.extern.java.Log;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

@Service
public class LogementServiceImpt {

    private final LogementRepository logementRepository;

    private static int nbrafficher = 8;

    public LogementServiceImpt(LogementRepository logementRepository) {
        this.logementRepository = logementRepository;
    }


    public Logement ajouterNouveuLogment() {
        return null;

    }

    public List<Logement> afficherAlllogementById(Long id) {

        return logementRepository.findAll().stream().filter(val -> val.getProprietaire().getId() == id).toList();
    }

    public List<Logement> afficherAlllogementByindex(int index) {

        if (index > 0) {

            /*
            Pageable pageable = PageRequest.of(index - 1, nbrafficher);
            List<Logement> logementstriee1=logementRepository.findAll();
            Collections.sort(logementstriee1);
            Page<Logement> logementsPage = logementRepository.findAll(pageable);
            List<Logement> logementstriee=new ArrayList<>(logementsPage.getContent());


             */

            List<Logement> logementstriee1=logementRepository.findAll();
            Collections.sort(logementstriee1);

            return recupererLogementParIndex(logementstriee1,index,nbrafficher);
        } else{
            List<Logement> logementstriee1=logementRepository.findAll();
            Collections.sort(logementstriee1);

            return recupererLogementParIndex(logementstriee1,0,nbrafficher);
        }


    }

    public List<Logement> afficherAlllogmentBySearch(String search) {

        int ver = 0;
        int entier = 0;
        try {
            entier = Integer.parseInt(search);
            ver = 1;

        } catch (NumberFormatException e) {
            System.out.println("La chaîne ne peut pas être convertie en entier.");
            ver = 0;
        }

        if (ver == 1) {
            ver = 0;
            int finalEntier = entier;
            return logementRepository.findAll().stream().filter(val -> val.getNbrDechambre() == finalEntier || val.getNbrlit() == finalEntier).toList();
        } else {
            return logementRepository.findAll().stream().filter(val -> val.getAdresse().contains(search) || val.getDescription().contains(search) || val.getVille().getNom().contains(search) || val.getVille().getCodePostal().contains(search)).toList();
        }

    }

    public List<Logement> afficherAlllogmentBySearchandIndex(String search, int index) {

        int ver = 0;
        int entier = 0;

        try {
            entier = Integer.parseInt(search);
            ver = 1;

        } catch (NumberFormatException e) {
            System.out.println("La chaîne ne peut pas être convertie en entier.");
            ver = 0;
        }

        if (ver == 1) {
            ver = 0;
            int finalEntier = entier;

            List<Logement> logementstrie=logementRepository.findAll();
            Collections.sort(logementstrie);
            return recupererLogementParIndex(logementstrie.stream().filter(val -> val.getNbrDechambre() == finalEntier || val.getNbrlit() == finalEntier).toList(),index,nbrafficher);

        } else {
            List<Logement> logementstrie=logementRepository.findAll();
            Collections.sort(logementstrie);
            return recupererLogementParIndex(logementstrie.stream().filter(val -> val.getAdresse().contains(search) || val.getDescription().contains(search) || val.getVille().getNom().contains(search) || val.getVille().getCodePostal().contains(search)).toList(),index,nbrafficher);
        }

    }


    public List<Logement> afficherAlllogmentBySearchandvarible(String ville,Integer prix,Integer nbrchambre, int index) {

            List<Logement> logementstrie=logementRepository.findAll();
            Collections.sort(logementstrie);

            if(ville!=null && prix!=null &&nbrchambre!=null){

                return recupererLogementParIndex(logementstrie.stream().filter(val -> val.getVille().equals(ville) && val.getPrix()==prix && val.getNbrDechambre() == nbrchambre).toList(),index,nbrafficher);
            }

            if(ville!=null)
                logementstrie=logementstrie.stream().filter(val -> val.getAdresse().contains(ville) || val.getVille().getNom().contains(ville)).toList();

            if(prix!=null)
                logementstrie=logementstrie.stream().filter(val -> val.getPrix()==prix).toList();

            if(nbrchambre!=null)
                logementstrie=logementstrie.stream().filter(val -> val.getNbrDechambre()==nbrchambre).toList();

            return recupererLogementParIndex(logementstrie,index,nbrafficher);




    }


    public List<Logement> recupererLogementParIndex(List<Logement> logements,int index,int nbraffiche){
        int size=logements.size();
        index=index-1;
        int starindex=index*nbraffiche;
        int endindex=Math.min(starindex+nbraffiche,size);
        if(starindex<size){
            return  logements.subList(starindex,endindex);
        }


        return null;

    }

}