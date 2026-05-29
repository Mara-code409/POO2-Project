package com.elearning.model;

import java.util.ArrayList;
import java.util.List;

public class Instructor extends Utilizator {
    private String specializare;
    private List<Curs> cursuriPredate = new ArrayList<>();

    public Instructor() { super(); setRol("INSTRUCTOR"); }

    public Instructor(int id, String nume, String email, String parola, String specializare) {
        super(id, nume, email, parola, "INSTRUCTOR");
        this.specializare = specializare;
    }

    public String getSpecializare() { return specializare; }
    public void setSpecializare(String specializare) { this.specializare = specializare; }
    public List<Curs> getCursuriPredate() { return cursuriPredate; }
    public void addCurs(Curs c) { cursuriPredate.add(c); }

    @Override
    public String toString() {
        return "Instructor: " + getNume() + " | Specializare: " + specializare;
    }
}
