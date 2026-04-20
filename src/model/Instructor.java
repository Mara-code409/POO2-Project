package model;

import java.util.ArrayList;
import java.util.List;

public class Instructor extends Utilizator {
    private String specializare;
    private List<Curs> cursuriPredate;

    public Instructor(int id, String nume, String email, String parola, String specializare) {
        super(id, nume, email, parola, "INSTRUCTOR");
        this.specializare = specializare;
        this.cursuriPredate = new ArrayList<>();
    }

    public String getSpecializare() { return specializare; }
    public List<Curs> getCursuriPredate() { return cursuriPredate; }
    public void addCurs(Curs c) { cursuriPredate.add(c); }

    @Override
    public String toString() {
        return "Instructor: " + getNume() + " | Specializare: " + specializare;
    }
}