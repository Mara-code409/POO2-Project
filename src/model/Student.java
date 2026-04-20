package model;

import java.util.ArrayList;
import java.util.List;

public class Student extends Utilizator {
    private List<Inscriere> inscrieri;

    public Student(int id, String nume, String email, String parola) {
        super(id, nume, email, parola, "STUDENT");
        this.inscrieri = new ArrayList<>();
    }

    public List<Inscriere> getInscrieri() { return inscrieri; }
    public void addInscriere(Inscriere i) { inscrieri.add(i); }

    @Override
    public String toString() {
        return "Student: " + getNume() + " (" + getEmail() + ")";
    }
}