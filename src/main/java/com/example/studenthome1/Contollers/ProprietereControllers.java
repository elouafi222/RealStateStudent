package com.example.studenthome1.Contollers;


import com.example.studenthome1.dtos.LogementUpdateDto;
import com.example.studenthome1.dtos.Message;
import com.example.studenthome1.dtos.ProprietaireDTO;
import com.example.studenthome1.dtos.ProprietaireDTO2;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/proprietere")
public class ProprietereControllers {

    LogementServiceImpt logementServiceImpt;
    private final ProprietaireServiceImpl proprietaireService;
    private final JwtServiceImp jwtServiceImp;

    private final VilleRepository villeRepository;

    private final VilleServiceImp villeServiceImp;

    private final   ProprietaireRepository proprietaireRepository;


    public ProprietereControllers(ProprietaireServiceImpl proprietaireService,JwtServiceImp jwtServiceImp,LogementServiceImpt logementServiceImpt,VilleRepository villeRepository,VilleServiceImp villeServiceImp,ProprietaireRepository proprietaireRepository) {
        this.proprietaireService = proprietaireService;
        this.jwtServiceImp=jwtServiceImp;
        this.logementServiceImpt=logementServiceImpt;
        this.villeRepository=villeRepository;
        this.villeServiceImp=villeServiceImp;
        this.proprietaireRepository=proprietaireRepository;
    }



    @PostMapping("/create")
    public String createprop(@RequestBody SignUnRequest signUnRequest){

        if(!signUnRequest.getNom().isEmpty()&& !signUnRequest.getPrenon().isEmpty() && !signUnRequest.getEmail().isEmpty() && !signUnRequest.getAdresse().isEmpty() && !signUnRequest.getNumeroTel().isEmpty() &&!signUnRequest.getTypePropritaire().isEmpty()&& !signUnRequest.getPassword().isEmpty()){


            proprietaireService.ajouterpropiretre(signUnRequest.getNom(), signUnRequest.getPrenon(),signUnRequest.getEmail(), signUnRequest.getAdresse(), signUnRequest.getNumeroTel(), signUnRequest.getTypePropritaire(), signUnRequest.getPassword(),null);
            return new Message("bien creer").toString();
        }

        return new Message("un ou plusieur champ vide").toString();
    }


    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteprop(@PathVariable int id){
         int val= proprietaireService.deleteProprietere((long)id);
         if(val==1)
             return ResponseEntity.ok().body("bien suprirmer");

         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("the id is not correct");
        }


     @PutMapping("/update/{id}")
     public ResponseEntity<?> update(@PathVariable int id,@RequestBody ProprietaireDTO2 proprietaireDTO2) {


    Proprietaire proprietaire=proprietaireRepository.findById((long)id).orElse(null);
    if(proprietaire!=null){
        proprietaire.setNom(proprietaireDTO2.getNom());
        proprietaire.setPrenom(proprietaireDTO2.getPrenon());
        proprietaire.setAdresse(proprietaireDTO2.getAdresse());
        proprietaire.setNumeroTel(proprietaireDTO2.getNumeroTel());
        proprietaireRepository.save(proprietaire);

        return ResponseEntity.ok(proprietaire);
    }

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("the id of user is not correct");

}

    @GetMapping("/afficherByid")
    public ResponseEntity<?> afficherbyid(@RequestParam long id){

        Proprietaire proprietaire= proprietaireRepository.findById((id)).orElse(null);
        if(proprietaire==null)
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("propriatiare is not found");
        return ResponseEntity.ok(proprietaire);

    }

    @PostMapping("/ajouterLogement")
    public ResponseEntity<?> ajouterLogement(HttpServletRequest request, @RequestBody LogementModel logementModel) {
        Proprietaire proprietaire = null;

        String jwt = request.getHeader("Authorization");

        if (jwt != null && jwt.startsWith("Bearer ")) {
            String token = jwt.substring(7);
            Long proprietaireId = jwtServiceImp.extractId(token);

            Ville ville = villeServiceImp.findByname(logementModel.getVilleNon());
            if (ville == null) {
                ville = new Ville(logementModel.getVilleNon(), logementModel.getCodePostal());
                Ville savedVille = villeRepository.save(ville);
            }

            if (logementModel.getAdresse() == null || logementModel.getAdresse().isEmpty() ||
                    logementModel.getDescription() == null || logementModel.getDescription().isEmpty() ||
                    logementModel.getCodePostal() == null || logementModel.getCodePostal().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tous les champs doivent être remplis.");

        } else {
                // Créer une nouvelle instance de Logement avec la Ville sauvegardée
                Logement logement = new Logement(logementModel.getSuperficie(), logementModel.getAdresse(),
                        logementModel.getDescription(), logementModel.getPrix(),
                        true, ville, null, null, null, proprietaire, null, logementModel.getNbrDechambre(), logementModel.getNbrlit());

                // Ajouter les images au logement
                List<Image> images = new ArrayList<>();
                logementModel.getImages().forEach(val -> images.add(new Image(val, logement)));
                logement.setImages(images);

                // Ajouter le logement au propriétaire
                proprietaireService.ajouterLogement(proprietaireId, logement);

                return ResponseEntity.ok(logement);
            }
        } else {
            System.out.println("-------------------------------- probleme");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token d'autorisation manquant ou invalide.");
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
