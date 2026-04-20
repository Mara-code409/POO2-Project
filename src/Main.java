import model.*;

import java.util.Arrays;

import service.ELearningService;

public class Main {
    public static void main(String[] args) {

        ELearningService service = ELearningService.getInstance();

        // Setup instructor
        Instructor instructor = new Instructor(0, "Prof. Ionescu", "ionescu@edu.ro", "pass123", "Programare");

        // 1. Inregistrare studenti
        System.out.println(" 1. Inregistrare studenti ");
        Student s1 = service.inregistrareStudent("Ana Pop", "ana@mail.com", "pass1");
        Student s2 = service.inregistrareStudent("Alexandru Dan", "alex@mail.com", "pass2");

        // 2. Adaugare cursuri
        System.out.println("\n 2. Adaugare cursuri ");
        Curs c1 = service.adaugareCurs("Java pentru incepatori", "Curs de baza Java", instructor, "Programare", 199.0);
        Curs c2 = service.adaugareCurs("Algoritmi si structuri de date", "ASD complet", instructor, "Programare", 249.0);
        Curs c3 = service.adaugareCurs("Machine Learning", "Intro ML", instructor, "Data Science", 349.0);

        // 3. Inscriere
        System.out.println("\n 3. Inscriere studenti ");
        service.inscriere(s1, c1);
        service.inscriere(s1, c3);
        service.inscriere(s2, c1);

        // 4. Adaugare module
        System.out.println("\n 4. Adaugare module ");
        service.adaugareModul("Introducere in Java", "Variabile, tipuri, operatori", 45, c1);
        service.adaugareModul("OOP in Java", "Clase, obiecte, mostenire", 60, c1);

        // 5. Vizualizare cursuri cu filtrare
        System.out.println("\n 5. Cursuri categoria Programare ");
        service.getCursuri("Programare");

        // 6. Cursuri student
        System.out.println("\n 6. Cursurile Anei ");
        service.getCursuriStudent(s1);

        // 7. Quiz
        System.out.println("\n 7. Sustinere quiz ");
        Intrebare q1 = new Intrebare(1, "Ce este JVM?", Arrays.asList("O cafea", "Java Virtual Machine", "Un IDE"), 1);
        Intrebare q2 = new Intrebare(2, "Ce face println?", Arrays.asList("Citeste", "Printeaza", "Sterge"), 1);
        Quiz quiz = new Quiz(99, c1, Arrays.asList(q1, q2), 10);
        service.adaugaQuiz(quiz);
        service.sustineQuiz(quiz, Arrays.asList(1, 1)); // ambele corecte

        // 8. Progres
        System.out.println("\n 8. Progres Ana la Java ");
        s1.getInscrieri().get(0).setProgres(75);
        service.vizualizeazaProgres(s1, c1);

        // 9. Studenti la curs
        System.out.println("\n 9. Studenti la Java pentru incepatori ");
        service.getStudentiLaCurs(c1);

        // 10. Stergere curs
        System.out.println("\n 10. Stergere curs ML ");
        service.stergeCurs(c3);
        System.out.println("Cursuri ramase:");
        service.getCursuri(null);
    }
}