package service;

import model.*;

import java.util.*;
import java.util.stream.Collectors;

public class ELearningService {

    private static ELearningService instance;

    private List<Student> studenti = new ArrayList<>();
    private List<Instructor> instructori = new ArrayList<>();
    private List<Curs> cursuri = new ArrayList<>();
    private List<Inscriere> inscrieri = new ArrayList<>();
    private Map<Integer, Quiz> quizuri = new HashMap<>();

    private int idCounter = 1;

    private ELearningService() {}

    public static ELearningService getInstance() {
        if (instance == null) instance = new ELearningService();
        return instance;
    }

    // 1. Inregistrare student
    public Student inregistrareStudent(String nume, String email, String parola) {
        Student s = new Student(idCounter++, nume, email, parola);
        studenti.add(s);
        System.out.println("Student inregistrat: " + s);
        return s;
    }

    // 2. Adaugare curs
    public Curs adaugareCurs(String titlu, String descriere, Instructor instructor, String categorie, double pret) {
        Curs c = new Curs(idCounter++, titlu, descriere, instructor, categorie, pret);
        cursuri.add(c);
        instructor.addCurs(c);
        System.out.println("Curs adaugat: " + c);
        return c;
    }

    // 3. Inscriere student la curs
    public Inscriere inscriere(Student student, Curs curs) {
        boolean deja = inscrieri.stream()
                .anyMatch(i -> i.getStudent().equals(student) && i.getCurs().equals(curs));
        if (deja) {
            System.out.println("Studentul e deja inscris la acest curs.");
            return null;
        }
        Inscriere i = new Inscriere(idCounter++, student, curs);
        inscrieri.add(i);
        student.addInscriere(i);
        System.out.println("Inscriere realizata: " + i);
        return i;
    }

    // 4. Adaugare modul la curs
    public Modul adaugareModul(String titlu, String continut, int durata, Curs curs) {
        Modul m = new Modul(idCounter++, titlu, continut, durata, curs);
        curs.addModul(m);
        System.out.println("Modul adaugat: " + m);
        return m;
    }

    // 5. Vizualizare cursuri (cu filtrare optionala dupa categorie)
    public List<Curs> getCursuri(String categorie) {
        List<Curs> rezultat = cursuri.stream()
                .filter(c -> categorie == null || c.getCategorie().equalsIgnoreCase(categorie))
                .sorted()
                .collect(Collectors.toList());
        rezultat.forEach(System.out::println);
        return rezultat;
    }

    // 6. Cursuri la care e inscris un student
    public List<Curs> getCursuriStudent(Student student) {
        List<Curs> rezultat = student.getInscrieri().stream()
                .map(Inscriere::getCurs)
                .collect(Collectors.toList());
        System.out.println("Cursuri ale studentului " + student.getNume() + ":");
        rezultat.forEach(System.out::println);
        return rezultat;
    }

    // 7. Sustinere quiz
    public double sustineQuiz(Quiz quiz, List<Integer> raspunsuri) {
        List<Intrebare> intrebari = quiz.getIntrebari();
        int corecte = 0;
        for (int i = 0; i < intrebari.size(); i++) {
            if (i < raspunsuri.size() && intrebari.get(i).esteCorect(raspunsuri.get(i))) {
                corecte++;
            }
        }
        double scor = (double) corecte / intrebari.size() * quiz.getPunctajMaxim();
        System.out.println("Scor quiz: " + scor + "/" + quiz.getPunctajMaxim());
        return scor;
    }

    // 8. Vizualizare progres student la un curs
    public void vizualizeazaProgres(Student student, Curs curs) {
        inscrieri.stream()
                .filter(i -> i.getStudent().equals(student) && i.getCurs().equals(curs))
                .findFirst()
                .ifPresentOrElse(
                        i -> System.out.println("Progres: " + i.getProgres() + "% | Nota: " + i.getNotaFinala()),
                        () -> System.out.println("Studentul nu este inscris la acest curs.")
                );
    }

    // 9. Studenti inscrisi la un curs
    public List<Student> getStudentiLaCurs(Curs curs) {
        List<Student> rezultat = inscrieri.stream()
                .filter(i -> i.getCurs().equals(curs))
                .map(Inscriere::getStudent)
                .collect(Collectors.toList());
        System.out.println("Studenti la cursul " + curs.getTitlu() + ":");
        rezultat.forEach(System.out::println);
        return rezultat;
    }

    // 10. Stergere curs
    public void stergeCurs(Curs curs) {
        cursuri.remove(curs);
        inscrieri.removeIf(i -> i.getCurs().equals(curs));
        System.out.println("Curs sters: " + curs.getTitlu());
    }

    public void adaugaQuiz(Quiz q) {
        quizuri.put(q.getId(), q);
    }
}