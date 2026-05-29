package com.elearning.model;

import java.time.LocalDate;

public class Inscriere {
    private int id;
    private int studentId;
    private int cursId;
    private LocalDate dataInscriere;
    private double progres;
    private double notaFinala;
    private double scorQuiz;

    public Inscriere() {}

    public Inscriere(int id, int studentId, int cursId) {
        this.id = id;
        this.studentId = studentId;
        this.cursId = cursId;
        this.dataInscriere = LocalDate.now();
        this.progres = 0;
        this.notaFinala = -1;
        this.scorQuiz = -1;
    }

    public int getId() { return id; }
    public int getStudentId() { return studentId; }
    public int getCursId() { return cursId; }
    public LocalDate getDataInscriere() { return dataInscriere; }
    public double getProgres() { return progres; }
    public double getNotaFinala() { return notaFinala; }

    public void setId(int id) { this.id = id; }
    public void setStudentId(int studentId) { this.studentId = studentId; }
    public void setCursId(int cursId) { this.cursId = cursId; }
    public void setDataInscriere(LocalDate dataInscriere) { this.dataInscriere = dataInscriere; }
    public double getScorQuiz() { return scorQuiz; }
    public void setProgres(double progres) { this.progres = progres; }
    public void setNotaFinala(double notaFinala) { this.notaFinala = notaFinala; }
    public void setScorQuiz(double scorQuiz) { this.scorQuiz = scorQuiz; }

    @Override
    public String toString() {
        return "Inscriere studentId=" + studentId + " cursId=" + cursId + " progres=" + progres + "%";
    }
}
