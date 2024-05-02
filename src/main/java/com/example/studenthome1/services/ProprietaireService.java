package com.example.studenthome1.services;

import com.example.studenthome1.entities.Logement;
import com.example.studenthome1.entities.Proprietaire;

import java.util.List;

public interface ProprietaireService {

    public  void ajouterpropiretre(String nom, String prenom,String email, String adresse, String numeroTel, String typePropritaire,String password, List<Logement> logements);

    public  Proprietaire getById(Long id);
    public Proprietaire getByEmail(String email);

    public int deleteProprietere(Long id);

    public List<Proprietaire> afficherAllProprietere();

   public List<Logement> getAllLogementbyid(Long id);

   public List<Logement> getAllLogementbyEmail(String email);

   public  void ajouterLogement(Long id,Logement logement);

   public long getidByemail(String email);

}
