package com.example.studenthome1.model;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

public class ReservationModel {
    private Long idEtudiant;
    private int idLogement;
    private Date dateDebut;
    private Date dateFin;

    public ReservationModel(Long idEtudiant, int idLogement, Date dateDebut, Date dateFin) {
        this.idEtudiant = idEtudiant;
        this.idLogement = idLogement;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public Long getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(Long idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public int getIdLogement() {
        return idLogement;
    }

    public void setIdLogement(int idLogement) {
        this.idLogement = idLogement;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
}
