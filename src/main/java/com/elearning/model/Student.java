package com.elearning.model;

import java.util.ArrayList;
import java.util.List;

public class Student extends Utilizator {
    private List<Inscriere> inscrieri = new ArrayList<>();

    public Student() { super(); setRol("STUDENT"); }

    public Student(int id, String nume, String email, String parola) {
        super(id, nume, email, parola, "STUDENT");
    }

    public List<Inscriere> getInscrieri() { return inscrieri; }
    public void setInscrieri(List<Inscriere> inscrieri) { this.inscrieri = inscrieri; }
    public void addInscriere(Inscriere i) { inscrieri.add(i); }

    @Override
    public String toString() {
        return "Student: " + getNume() + " (" + getEmail() + ")";
    }
}
