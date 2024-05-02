package com.example.studenthome1.services;

import com.example.studenthome1.entities.Proprietaire;
import com.example.studenthome1.repositories.ProprietaireRepository;
import org.hibernate.annotations.Comment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {


    private ProprietaireRepository proprietaireRepository;

    public UserDetailsServiceImpl(ProprietaireRepository proprietaireRepository){
        this.proprietaireRepository=proprietaireRepository;

    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return null;
    }
}
