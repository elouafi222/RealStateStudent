package com.example.studenthome1.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String chemain;



    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "logement_id")
    private Logement logement;


    public Image(String chemain,Logement logement){
        this.chemain=chemain;
        this.logement=logement;
    }

}
