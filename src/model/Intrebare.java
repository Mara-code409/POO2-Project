package model;

import java.util.List;

public class Intrebare {
    private int id;
    private String text;
    private List<String> varianteRaspuns;
    private int indexRaspunsCorect;

    public Intrebare(int id, String text, List<String> varianteRaspuns, int indexRaspunsCorect) {
        this.id = id;
        this.text = text;
        this.varianteRaspuns = varianteRaspuns;
        this.indexRaspunsCorect = indexRaspunsCorect;
    }

    public int getId() { return id; }
    public String getText() { return text; }
    public List<String> getVarianteRaspuns() { return varianteRaspuns; }
    public int getIndexRaspunsCorect() { return indexRaspunsCorect; }

    public boolean esteCorect(int indexAles) {
        return indexAles == indexRaspunsCorect;
    }

    @Override
    public String toString() {
        return "Intrebare: " + text;
    }
}