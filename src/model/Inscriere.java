package model;

import java.time.LocalDate;

public class Inscriere {
    private int id;
    private Student student;
    private Curs curs;
    private LocalDate data;
    private double progres; // 0-100%
    private double notaFinala;

    public Inscriere(int id, Student student, Curs curs) {
        this.id = id;
        this.student = student;
        this.curs = curs;
        this.data = LocalDate.now();
        this.progres = 0;
        this.notaFinala = -1;
    }

    public int getId() { return id; }
    public Student getStudent() { return student; }
    public Curs getCurs() { return curs; }
    public LocalDate getData() { return data; }
    public double getProgres() { return progres; }
    public double getNotaFinala() { return notaFinala; }

    public void setProgres(double progres) { this.progres = progres; }
    public void setNotaFinala(double nota) { this.notaFinala = nota; }

    @Override
    public String toString() {
        return student.getNume() + " -> " + curs.getTitlu() + " | Progres: " + progres + "%";
    }
}