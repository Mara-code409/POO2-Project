package com.elearning.model;

import java.util.ArrayList;
import java.util.List;

public class Curs implements Comparable<Curs> {
    private int id;
    private String titlu;
    private String descriere;
    private Instructor instructor;
    private String categorie;
    private double pret;
    private List<Modul> module = new ArrayList<>();

    public Curs() {}

    public Curs(int id, String titlu, String descriere, Instructor instructor, String categorie, double pret) {
        this.id = id;
        this.titlu = titlu;
        this.descriere = descriere;
        this.instructor = instructor;
        this.categorie = categorie;
        this.pret = pret;
    }

    public int getId() { return id; }
    public String getTitlu() { return titlu; }
    public String getDescriere() { return descriere; }
    public Instructor getInstructor() { return instructor; }
    public String getCategorie() { return categorie; }
    public double getPret() { return pret; }
    public List<Modul> getModule() { return module; }

    public void setId(int id) { this.id = id; }
    public void setTitlu(String titlu) { this.titlu = titlu; }
    public void setDescriere(String descriere) { this.descriere = descriere; }
    public void setInstructor(Instructor instructor) { this.instructor = instructor; }
    public void setCategorie(String categorie) { this.categorie = categorie; }
    public void setPret(double pret) { this.pret = pret; }
    public void setModule(List<Modul> module) { this.module = module; }
    public void addModul(Modul m) { module.add(m); }

    @Override
    public int compareTo(Curs other) { return this.titlu.compareTo(other.titlu); }

    @Override
    public String toString() {
        return "Curs: " + titlu + " | Categorie: " + categorie + " | Pret: " + pret + " RON";
    }
}
