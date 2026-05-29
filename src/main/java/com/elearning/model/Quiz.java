package com.elearning.model;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    private int id;
    private int cursId;
    private List<Intrebare> intrebari = new ArrayList<>();
    private int punctajMaxim;

    public Quiz() {}

    public Quiz(int id, int cursId, List<Intrebare> intrebari, int punctajMaxim) {
        this.id = id;
        this.cursId = cursId;
        this.intrebari = intrebari;
        this.punctajMaxim = punctajMaxim;
    }

    public int getId() { return id; }
    public int getCursId() { return cursId; }
    public List<Intrebare> getIntrebari() { return intrebari; }
    public int getPunctajMaxim() { return punctajMaxim; }

    public void setId(int id) { this.id = id; }
    public void setCursId(int cursId) { this.cursId = cursId; }
    public void setIntrebari(List<Intrebare> intrebari) { this.intrebari = intrebari; }
    public void setPunctajMaxim(int punctajMaxim) { this.punctajMaxim = punctajMaxim; }

    @Override
    public String toString() { return "Quiz cursId=" + cursId + " | " + intrebari.size() + " intrebari"; }
}
