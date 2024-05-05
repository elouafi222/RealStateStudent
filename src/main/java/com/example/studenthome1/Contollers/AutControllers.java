package com.example.studenthome1.Contollers;



import com.example.studenthome1.dtos.AuthRequestDTO;
import com.example.studenthome1.dtos.JwtResponseDTO;
import com.example.studenthome1.dtos.Message;
import com.example.studenthome1.entities.Proprietaire;
import com.example.studenthome1.mappers.ProprietaireMapper;
import com.example.studenthome1.model.SignUnRequest;

import com.example.studenthome1.repositories.PartuculierReposotory;
import com.example.studenthome1.repositories.ProprietaireRepository;
import com.example.studenthome1.services.ProprietaireService;
import com.example.studenthome1.services.impl.ProprietaireServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import com.example.studenthome1.services.JwtServiceImp;

import org.springframework.security.authentication.AuthenticationManager;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/aut")
public class AutControllers {


    private final ProprietaireRepository proprietaireRepository;
    private final AuthenticationManager authenticationManager;

    private final ProprietaireServiceImpl proprietaireService;

    private final JwtServiceImp jwtServiceImp;

    public AutControllers(ProprietaireRepository proprietaireRepository,AuthenticationManager authenticationManager,JwtServiceImp jwtServiceImp,ProprietaireServiceImpl proprietaireService) {
        this.proprietaireRepository = proprietaireRepository;
        this.authenticationManager=authenticationManager;
        this.jwtServiceImp=jwtServiceImp;
        this.proprietaireService=proprietaireService;
    }

    @PostMapping("/signInParticulier")
    public ResponseEntity<?> AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO){

        Proprietaire proprietaire= proprietaireService.getByEmail(authRequestDTO.getEmail());
        if(proprietaire!=null){
            long id= proprietaire.getId();
            return ResponseEntity.ok(new  JwtResponseDTO(jwtServiceImp.GenerateToken(authRequestDTO.getEmail(),id)));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("in des champs et invalid");
        /*
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getEmail(), authRequestDTO.getPassword()));
        if(authentication.isAuthenticated()){

            return new JwtResponseDTO(jwtServiceImp.GenerateToken(authRequestDTO.getEmail()));
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }

         */
    }

    @PostMapping("/signUpParticulier")
    public ResponseEntity<?> signupPrt(@RequestBody SignUnRequest signUnRequest){

        if(!signUnRequest.getNom().isEmpty() && !signUnRequest.getNom().equals("") && !signUnRequest.getPrenon().isEmpty()&& !signUnRequest.getEmail().isEmpty() && !signUnRequest.getEmail().equals("")&&!signUnRequest.getAdresse().isEmpty()&& !signUnRequest.getAdresse().equals("")&&!signUnRequest.getNumeroTel().isEmpty()&&!signUnRequest.getNumeroTel().equals("") && !signUnRequest.getPassword().isEmpty()&& !signUnRequest.getPassword().equals("")){

            Proprietaire objcreer = new Proprietaire(signUnRequest.getNom(), signUnRequest.getPrenon(),signUnRequest.getEmail() ,signUnRequest.getAdresse(), signUnRequest.getNumeroTel(), signUnRequest.getTypePropritaire(),signUnRequest.getPassword(), null);
            proprietaireRepository.save(objcreer);

            return ResponseEntity.ok(ProprietaireMapper.mapToProprietaireDTO(objcreer));

        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("remplir tout les champ");

    }




}
