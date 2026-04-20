package model;

public class Modul {
    private int id;
    private String titlu;
    private String continut;
    private int durata; // minute
    private Curs curs;

    public Modul(int id, String titlu, String continut, int durata, Curs curs) {
        this.id = id;
        this.titlu = titlu;
        this.continut = continut;
        this.durata = durata;
        this.curs = curs;
    }

    public int getId() { return id; }
    public String getTitlu() { return titlu; }
    public String getContinut() { return continut; }
    public int getDurata() { return durata; }
    public Curs getCurs() { return curs; }

    @Override
    public String toString() {
        return "Modul: " + titlu + " (" + durata + " min)";
    }
}