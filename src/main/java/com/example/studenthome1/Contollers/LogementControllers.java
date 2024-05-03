package com.example.studenthome1.Contollers;


import com.example.studenthome1.dtos.Message;
import com.example.studenthome1.entities.Logement;
import com.example.studenthome1.entities.Proprietaire;
import com.example.studenthome1.entities.Ville;
import com.example.studenthome1.model.LogementModel;
import com.example.studenthome1.repositories.LogementRepository;
import com.example.studenthome1.services.JwtServiceImp;
import com.example.studenthome1.services.LogementServiceImpt;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/logement")
public class LogementControllers {

    private final  LogementRepository logementRepository;
    private final JwtServiceImp jwtServiceImp;

    private final LogementServiceImpt logementServiceImpt;
    public LogementControllers(LogementRepository logementRepository,JwtServiceImp jwtServiceImp,LogementServiceImpt logementServiceImpt){

        this.logementRepository=logementRepository;
        this.jwtServiceImp=jwtServiceImp;
        this.logementServiceImpt=logementServiceImpt;

    }


    @GetMapping("/afficherlogmentbyid")
    public Logement ajouterLogement(@RequestBody int id) {

        return logementRepository.findById(id).orElse(null);

    }


    @GetMapping("/delete")
    public String delete(@RequestBody int id) {

        Logement logement=logementRepository.findById(id).orElse(null);
        if(logement!=null){
            logementRepository.delete(logement);
            return new Message("suprimer").toString();
        }
        return new Message("probleme dans id").toString();

    }

    @GetMapping("/deleteAll")
    public String delete() {


        logementRepository.deleteAll();
        return new Message("surpmer tout").toString();

    }


    @GetMapping("/afficherAlllogement")
    public List<Logement> ajouterLogement() {
        Proprietaire proprietaire = null;
        return logementRepository.findAll();

    }


    @GetMapping("/afficherAllLogementByindex")
    public List<Logement> afficherAlllogmentByindex(@RequestParam int index){
        Proprietaire proprietaire=null;

        if(index>0){
           return logementServiceImpt.afficherAlllogementByindex(index);
        }
         return null;
    }

    @GetMapping("/afficherAllLogementBySearch")
    public List<Logement> afficherAlllogmentBySearch(@RequestParam String search){
        Proprietaire proprietaire=null;

        if(!search.isEmpty() && ! search.equals("")){
            return logementServiceImpt.afficherAlllogmentBySearch(search);
        }
        return null;
    }
}
