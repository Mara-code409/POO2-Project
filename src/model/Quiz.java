package model;

import java.util.List;

public class Quiz {
    private int id;
    private Curs curs;
    private List<Intrebare> intrebari;
    private int punctajMaxim;

    public Quiz(int id, Curs curs, List<Intrebare> intrebari, int punctajMaxim) {
        this.id = id;
        this.curs = curs;
        this.intrebari = intrebari;
        this.punctajMaxim = punctajMaxim;
    }

    public int getId() { return id; }
    public Curs getCurs() { return curs; }
    public List<Intrebare> getIntrebari() { return intrebari; }
    public int getPunctajMaxim() { return punctajMaxim; }

    @Override
    public String toString() {
        return "Quiz pentru: " + curs.getTitlu() + " | " + intrebari.size() + " intrebari";
    }
}