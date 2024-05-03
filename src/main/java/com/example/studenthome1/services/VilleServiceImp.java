package com.example.studenthome1.services;

import com.example.studenthome1.entities.Ville;
import com.example.studenthome1.repositories.VilleRepository;
import org.springframework.stereotype.Service;

@Service
public class VilleServiceImp {

    private final VilleRepository villeRepository;

    public VilleServiceImp(VilleRepository villeRepository){
        this.villeRepository=villeRepository;
    }


    public Ville findByname(String name){

        Ville ville= villeRepository.findByName(name).orElse(null);
        return ville;
    }

}
