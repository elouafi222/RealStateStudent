package com.example.studenthome1.services;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.Objects;

public interface JwtService {

    public String generateToken(UserDetails userDetails);

    public String createToken(Map<String, Objects> claims,String subject);

    public Boolean validateToken(String token);

    public String extractUsername(String token);

}
