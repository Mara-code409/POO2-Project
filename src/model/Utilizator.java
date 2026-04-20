package model;

public class Utilizator {
    private int id;
    private String nume;
    private String email;
    private String parola;
    private String rol;

    public Utilizator(int id, String nume, String email, String parola, String rol) {
        this.id = id;
        this.nume = nume;
        this.email = email;
        this.parola = parola;
        this.rol = rol;
    }

    public int getId() { return id; }
    public String getNume() { return nume; }
    public String getEmail() { return email; }
    public String getParola() { return parola; }
    public String getRol() { return rol; }

    public void setNume(String nume) { this.nume = nume; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "[" + rol + "] " + nume + " (" + email + ")";
    }
}