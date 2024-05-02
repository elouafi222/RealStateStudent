package com.example.studenthome1.Contollers;


import com.example.studenthome1.entities.Image;
import com.example.studenthome1.entities.Logement;
import com.example.studenthome1.entities.Proprietaire;
import com.example.studenthome1.entities.Ville;
import com.example.studenthome1.model.LogementModel;
import com.example.studenthome1.model.SignUnRequest;
import com.example.studenthome1.repositories.ProprietaireRepository;
import com.example.studenthome1.repositories.VilleRepository;
import com.example.studenthome1.services.JwtServiceImp;
import com.example.studenthome1.services.LogementServiceImpt;
import com.example.studenthome1.services.VilleServiceImp;
import com.example.studenthome1.services.impl.ProprietaireServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/proprietere")
public class ProprietereControllers {

    LogementServiceImpt logementServiceImpt;
    private final ProprietaireServiceImpl proprietaireService;
    private final JwtServiceImp jwtServiceImp;

    private final VilleRepository villeRepository;

    private final VilleServiceImp villeServiceImp;


    public ProprietereControllers(ProprietaireServiceImpl proprietaireService,JwtServiceImp jwtServiceImp,LogementServiceImpt logementServiceImpt,VilleRepository villeRepository,VilleServiceImp villeServiceImp) {
        this.proprietaireService = proprietaireService;
        this.jwtServiceImp=jwtServiceImp;
        this.logementServiceImpt=logementServiceImpt;
        this.villeRepository=villeRepository;
        this.villeServiceImp=villeServiceImp;
    }



    @PostMapping("/create")
    public void createprop(@RequestBody SignUnRequest signUnRequest){

        proprietaireService.ajouterpropiretre(signUnRequest.getNom(), signUnRequest.getPrenon(),signUnRequest.getEmail(), signUnRequest.getAdresse(), signUnRequest.getNumeroTel(), signUnRequest.getTypePropritaire(), signUnRequest.getPassword(),null);

    }

    @PostMapping("/delete")
    public void deleteprop(@RequestBody long id){

        proprietaireService.deleteProprietere(id);

    }

    @PostMapping("/ajouterLogement")
    public Logement ajouterLogement(HttpServletRequest request, @RequestBody LogementModel logementModel) {
        Proprietaire proprietaire = null;

        String jwt = request.getHeader("Authorization");

        if (jwt != null && jwt.startsWith("Bearer ")) {
            String token = jwt.substring(7);
            Long proprietaireId = jwtServiceImp.extractId(token);


            Ville ville= villeServiceImp.findByname(logementModel.getVilleNon());
            if(ville==null){
                ville = new Ville(logementModel.getVilleNon(), logementModel.getCodePostal());
                Ville savedVille = villeRepository.save(ville);
            }

            // Créer une nouvelle instance de Logement avec la Ville sauvegardée
            Logement logement = new Logement(logementModel.getSuperficie(), logementModel.getAdresse(),
                    logementModel.getDescription(), logementModel.getPrix(),
                    true, ville, null, null, null, proprietaire, null);

            // Ajouter les images au logement
            List<Image> images = new ArrayList<>();
            logementModel.getImages().forEach(val -> images.add(new Image(val, logement)));
            logement.setImages(images);

            // Ajouter le logement au propriétaire
            proprietaireService.ajouterLogement(proprietaireId, logement);

            return logement;
        } else {
            System.out.println("-------------------------------- probleme");
            return null;
        }

    }


    @PostMapping("/afficherAllLogementByid")
    public List<Logement> afficherAlllogmentByid(HttpServletRequest request){
        Proprietaire proprietaire=null;

        String jwt = request.getHeader("Authorization");

        if (jwt != null && jwt.startsWith("Bearer ")) {
            String token = jwt.substring(7);
            Long  proprietaireId = jwtServiceImp.extractId(jwt);

            return logementServiceImpt.afficherAlllogementById(proprietaireId);

        } else
            return null;

    }


    @GetMapping("/afficherAllPropritere")
    public List<Proprietaire> afficherall(){

        return  proprietaireService.afficherAllProprietere();

    }



}
