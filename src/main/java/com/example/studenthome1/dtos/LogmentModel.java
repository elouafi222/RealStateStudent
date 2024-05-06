package com.example.studenthome1.dtos;

public class LogmentModel implements Comparable<LogmentModel> {

    private long id;
    private float superficie;
    private String Adresse;
    private String Description;
    private float prix;
    private boolean disponible;

    private int nbrDechambre;
    private int nbrlit;
    @Override
    public int compareTo(LogmentModel o) {
        return 0;
    }
}
