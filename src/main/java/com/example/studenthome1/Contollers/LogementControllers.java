package com.example.studenthome1.Contollers;


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
}
