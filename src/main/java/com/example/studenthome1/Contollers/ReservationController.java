package com.example.studenthome1.Contollers;

import com.example.studenthome1.entities.Etudiant;
import com.example.studenthome1.entities.Logement;
import com.example.studenthome1.entities.Location;
import com.example.studenthome1.model.ReservationModel;
import com.example.studenthome1.repositories.EtudiantRepository;
import com.example.studenthome1.repositories.LocationRepository;
import com.example.studenthome1.repositories.LogementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Reservation")
public class ReservationController {

    @Autowired
    private LocationRepository locationRepository;

    private final EtudiantRepository etudiantRepository;
    private final LogementRepository logementRepository;

    public ReservationController(LocationRepository locationRepository, EtudiantRepository etudiantRepository, LogementRepository logementRepository) {
        this.locationRepository = locationRepository;
        this.etudiantRepository = etudiantRepository;
        this.logementRepository = logementRepository;
    }

    @PostMapping("/reservation")
    public String faireReservation(@RequestBody ReservationModel reservationModel) {

        // Vous devriez récupérer l'étudiant et le logement correspondant aux IDs à partir de votre repository

        Etudiant etudiant = etudiantRepository.findById(reservationModel.getIdEtudiant()).orElse(null);
        Logement logement = logementRepository.findById(reservationModel.getIdLogement()).orElse(null);

        if (etudiant == null || logement == null) {
            return "Étudiant ou logement non trouvé";
        }

        // Créer une nouvelle réservation
        Location reservation = new Location();
        reservation.setEtudiant(etudiant);
        reservation.setLogement(logement);
        reservation.setDateDébut(reservationModel.getDateDebut());
        reservation.setDateFin(reservationModel.getDateFin());

        logement.setDisponible(false);
        logementRepository.save(logement);

        // Enregistrer la réservation dans la base de données
        locationRepository.save(reservation);

        return "Réservation effectuée avec succès";
    }
}

