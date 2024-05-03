package com.example.studenthome1.model;

import lombok.Data;

import java.util.List;

@Data
public class ChambreModel {

    private String typechambre;

    private List<LitModel>  lit;
}
