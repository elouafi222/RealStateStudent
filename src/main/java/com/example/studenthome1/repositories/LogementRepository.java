package com.example.studenthome1.repositories;

import com.example.studenthome1.entities.Logement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LogementRepository extends JpaRepository<Logement, Integer> {
}