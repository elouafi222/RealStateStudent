package com.example.studenthome1.repositories;

import com.example.studenthome1.entities.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VilleRepository extends CrudRepository<Ville, Long> {
    @Query("SELECT v FROM Ville v WHERE v.nom = :nom")
    Optional<Ville> findByName(@Param("nom") String nom);
}
