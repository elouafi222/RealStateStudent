package com.example.studenthome1.Contollers;

import com.example.studenthome1.dtos.AuthRequestDTO;
import com.example.studenthome1.dtos.JwtResponseDTO;
import com.example.studenthome1.entities.Etudiant;
import com.example.studenthome1.entities.Proprietaire;
import com.example.studenthome1.mappers.EtudiantMapper;
import com.example.studenthome1.mappers.ProprietaireMapper;
import com.example.studenthome1.model.SignunRequest2;
import com.example.studenthome1.repositories.EtudiantRepository;
import com.example.studenthome1.services.JwtServiceImp;
import com.example.studenthome1.services.impl.EtudiantServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/authentificationStudent")
public class AutControllersStudent {

    private final EtudiantRepository etudiantRepository;
    private final AuthenticationManager authenticationManager;
    private final EtudiantServiceImpl etudiantService;
    private final JwtServiceImp jwtServiceImp;

    public AutControllersStudent(EtudiantRepository etudiantRepository, AuthenticationManager authenticationManager, EtudiantServiceImpl etudiantService, JwtServiceImp jwtServiceImp) {
        this.etudiantRepository = etudiantRepository;
        this.authenticationManager = authenticationManager;
        this.etudiantService = etudiantService;
        this.jwtServiceImp = jwtServiceImp;
    }

    @PostMapping("/signInStudent")
    public ResponseEntity<?> AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO) {

        Etudiant etudiant = etudiantService.getEtudiantByEmail(authRequestDTO.getEmail());
        if (etudiant != null) {
            long id = etudiant.getId();
            return ResponseEntity.ok(new JwtResponseDTO(jwtServiceImp.GenerateToken(authRequestDTO.getEmail(), id), etudiant.getId(), etudiant.getNom(), etudiant.getPrenom(), etudiant.getAdresseMail(), etudiant.getNumeroTel()));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("in des champs et invalid");

    }


        @PostMapping("/signUpStudent")
        public ResponseEntity<?> signupPrt(@RequestBody SignunRequest2 signunRequest2){

            if(!signunRequest2.getNom().isEmpty() && !signunRequest2.getNom().equals("") && !signunRequest2.getPrenom().isEmpty()&& !signunRequest2.getAdresseMail().isEmpty() && !signunRequest2.getDateNaissance().equals("") && !signunRequest2.getNumeroTel().isEmpty()&&!signunRequest2.getNumeroTel().equals("") && !signunRequest2.getPassword().isEmpty()&& !signunRequest2.getPassword().equals("")){

                Etudiant etudiant = new Etudiant(signunRequest2.getNom(), signunRequest2.getPrenom(),signunRequest2.getAdresseMail(), signunRequest2.getNumeroTel(), signunRequest2.getDateNaissance(), signunRequest2.getUniversite(), signunRequest2.getPassword());
                etudiantRepository.save(etudiant);

                return ResponseEntity.ok(EtudiantMapper.mapToEtudiantDTO(etudiant));

            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("remplir tout les champ");

        }
}
