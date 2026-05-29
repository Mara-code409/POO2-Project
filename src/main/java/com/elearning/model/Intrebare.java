package com.elearning.model;

import java.util.List;

public class Intrebare {
    private int id;
    private String text;
    private List<String> varianteRaspuns;
    private int indexRaspunsCorect;
    private int quizId;

    public Intrebare() {}

    public Intrebare(int id, String text, List<String> varianteRaspuns, int indexRaspunsCorect, int quizId) {
        this.id = id;
        this.text = text;
        this.varianteRaspuns = varianteRaspuns;
        this.indexRaspunsCorect = indexRaspunsCorect;
        this.quizId = quizId;
    }

    public int getId() { return id; }
    public String getText() { return text; }
    public List<String> getVarianteRaspuns() { return varianteRaspuns; }
    public int getIndexRaspunsCorect() { return indexRaspunsCorect; }
    public int getQuizId() { return quizId; }

    public void setId(int id) { this.id = id; }
    public void setText(String text) { this.text = text; }
    public void setVarianteRaspuns(List<String> varianteRaspuns) { this.varianteRaspuns = varianteRaspuns; }
    public void setIndexRaspunsCorect(int indexRaspunsCorect) { this.indexRaspunsCorect = indexRaspunsCorect; }
    public void setQuizId(int quizId) { this.quizId = quizId; }

    public boolean esteCorect(int indexAles) { return indexAles == indexRaspunsCorect; }

    @Override
    public String toString() { return "Intrebare: " + text; }
}
