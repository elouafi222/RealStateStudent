package com.example.studenthome1.Contollers;


import com.example.studenthome1.entities.Logement;
import com.example.studenthome1.entities.Proprietaire;
import com.example.studenthome1.entities.Ville;
import com.example.studenthome1.model.LogementModel;
import com.example.studenthome1.repositories.LogementRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logement")
public class LogementControllers {

    private final  LogementRepository logementRepository;
    public LogementControllers(LogementRepository logementRepository){

        this.logementRepository=logementRepository;

    }
    @GetMapping("/afficherAlllogement")
    public List<Logement> ajouterLogement() {
        Proprietaire proprietaire = null;
        return logementRepository.findAll();

    }
}
