package com.example.studenthome1.Contollers;


import com.example.studenthome1.dtos.LogementUpdateDto;
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
    public Logement ajouterLogement(@RequestParam int id) {

        return logementRepository.findById(id).orElse(null);

    }


    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id) {

        Logement logement = logementRepository.findById(id).orElse(null);
        if (logement != null) {
            logementRepository.delete(logement);
            return new Message("update logment").toString();
        }
        return new Message("problème dans l'ID").toString();

    }


    @PutMapping("/update/{id}")
    public String update(@PathVariable int id,@RequestBody LogementUpdateDto logementUpdateDto) {

        Logement logement=logementRepository.findById(id).orElse(null);
        if(logement!=null){
            logement.setSuperficie(logementUpdateDto.getSuperficie());
            logement.setAdresse(logementUpdateDto.getAdresse());
            logement.setDescription(logementUpdateDto.getDescription());
            logement.setPrix(logementUpdateDto.getPrix());
            logement.setDisponible(logementUpdateDto.isDisponible());

            logementRepository.save(logement);
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

    @GetMapping("/afficherAllLogementBySearchandIndex")
    public List<Logement> afficherAlllogmentBySearch(@RequestParam String search,@RequestParam int index){

        if(index<=0)
            index=1;

        if(!search.isEmpty() && ! search.equals("")){
            return logementServiceImpt.afficherAlllogmentBySearchandIndex(search,index);
        }

        if(index>0){
            return logementServiceImpt.afficherAlllogementByindex(index);
        }


        return null;
    }


    @GetMapping("/nbrLogmentSearch")
    public int afficherAlllogmentBySearch(@RequestParam String search){



        if(!search.isEmpty() && ! search.equals("")){
            return logementServiceImpt.afficherAlllogmentBySearch(search).size();
        }
        return 0;
    }







    @GetMapping("/alllogement")
    public int afficherAlllogmentBySearch(){

        return logementRepository.findAll().size();

    }

}
