package com.elearning.model;

public class Modul {
    private int id;
    private String titlu;
    private String continut;
    private int durata;
    private int cursId;

    public Modul() {}

    public Modul(int id, String titlu, String continut, int durata, int cursId) {
        this.id = id;
        this.titlu = titlu;
        this.continut = continut;
        this.durata = durata;
        this.cursId = cursId;
    }

    public int getId() { return id; }
    public String getTitlu() { return titlu; }
    public String getContinut() { return continut; }
    public int getDurata() { return durata; }
    public int getCursId() { return cursId; }

    public void setId(int id) { this.id = id; }
    public void setTitlu(String titlu) { this.titlu = titlu; }
    public void setContinut(String continut) { this.continut = continut; }
    public void setDurata(int durata) { this.durata = durata; }
    public void setCursId(int cursId) { this.cursId = cursId; }

    @Override
    public String toString() { return "Modul: " + titlu + " (" + durata + " min)"; }
}
