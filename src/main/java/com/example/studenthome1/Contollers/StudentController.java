package com.example.studenthome1.Contollers;

import com.example.studenthome1.dtos.EtudiantDTO;
import com.example.studenthome1.dtos.Message;
import com.example.studenthome1.entities.Etudiant;
import com.example.studenthome1.model.SignUnRequest;
import com.example.studenthome1.model.SignunRequest2;
import com.example.studenthome1.repositories.EtudiantRepository;
import com.example.studenthome1.services.impl.EtudiantServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etudiant")
public class StudentController {

    private final EtudiantServiceImpl etudiantService;
    private final EtudiantRepository etudiantRepository;

    public StudentController(EtudiantServiceImpl etudiantService, EtudiantRepository etudiantRepository) {
        this.etudiantService = etudiantService;
        this.etudiantRepository = etudiantRepository;
    }

    @PostMapping("/create")
    public String createEtudiant(@RequestBody SignunRequest2 signunRequest2){

        if(!signunRequest2.getNom().isEmpty()&& !signunRequest2.getPrenom().isEmpty() && !signunRequest2.getAdresseMail().isEmpty()  && !signunRequest2.getNumeroTel().isEmpty() && !signunRequest2.getUniversite().isEmpty() &&  !signunRequest2.getDateNaissance().isEmpty() && !signunRequest2.getPassword().isEmpty()){


            etudiantService.AjouterEtudiant(signunRequest2.getNom(), signunRequest2.getPrenom(),signunRequest2.getAdresseMail(), signunRequest2.getNumeroTel(), signunRequest2.getDateNaissance(), signunRequest2.getUniversite(), signunRequest2.getPassword());
            return new Message("bien creer").toString();
        }

        return new Message("un ou plusieur champ vide").toString();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteStud(@PathVariable int id){
        int val= etudiantService.deleteEtudiant((long)id);
        if(val==1)
            return ResponseEntity.ok().body("bien suprirmer");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("the id is not correct");
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable int id,@RequestBody EtudiantDTO etudiantDto) {


        Etudiant etudiant= etudiantRepository.findById((long)id).orElse(null);
        if(etudiant!=null){
            etudiant.setNom(etudiantDto.getNom());
            etudiant.setPrenom(etudiantDto.getPrenom());
            etudiant.setAdresseMail(etudiantDto.getAdresseMail());
            etudiant.setNumeroTel(etudiantDto.getNumeroTel());
            etudiantRepository.save(etudiant);

            return ResponseEntity.ok(etudiant);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("the id of user is not correct");

    }

    @GetMapping("/afficherById")
    public ResponseEntity<?> afficherEtudiantById(@RequestParam long id) {
        Etudiant etudiant = etudiantService.getEtudiantById(id);
        if (etudiant != null) {
            return ResponseEntity.ok(etudiant);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message("Étudiant non trouvé."));
    }

    @GetMapping("/afficherAll")
    public List<Etudiant> afficherAllEtudiants() {
        return etudiantService.getAllEtudiants();
    }

}
