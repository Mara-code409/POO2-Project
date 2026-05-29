package com.elearning.service;

import com.elearning.model.*;
import com.elearning.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ELearningService {

    private final StudentRepository studentRepo;
    private final InstructorRepository instructorRepo;
    private final CursRepository cursRepo;
    private final InscriereRepository inscriereRepo;
    private final QuizRepository quizRepo;
    private final ModulRepository modulRepo;
    private final AuditService audit;

    public ELearningService(StudentRepository studentRepo,
                            InstructorRepository instructorRepo,
                            CursRepository cursRepo,
                            InscriereRepository inscriereRepo,
                            QuizRepository quizRepo,
                            ModulRepository modulRepo,
                            AuditService audit) {
        this.studentRepo = studentRepo;
        this.instructorRepo = instructorRepo;
        this.cursRepo = cursRepo;
        this.inscriereRepo = inscriereRepo;
        this.quizRepo = quizRepo;
        this.modulRepo = modulRepo;
        this.audit = audit;
    }

    // 1. Inregistrare student
    public Student inregistrareStudent(String nume, String email, String parola) {
        Student s = new Student(0, nume, email, parola);
        studentRepo.save(s);
        audit.log("inregistrare_student");
        return s;
    }

    // 2. Adaugare curs
    public Curs adaugareCurs(String titlu, String descriere, int instructorId, String categorie, double pret) {
        Instructor instructor = instructorRepo.findById(instructorId)
            .orElseThrow(() -> new RuntimeException("Instructor negasit: " + instructorId));
        Curs c = new Curs(0, titlu, descriere, instructor, categorie, pret);
        cursRepo.save(c);
        audit.log("adaugare_curs");
        return c;
    }

    // 3. Inscriere student la curs
    public Inscriere inscriere(int studentId, int cursId) {
        if (inscriereRepo.findByStudentAndCurs(studentId, cursId).isPresent()) {
            throw new RuntimeException("Studentul este deja inscris la acest curs.");
        }
        Inscriere i = new Inscriere(0, studentId, cursId);
        inscriereRepo.save(i);
        audit.log("inscriere_student");
        return i;
    }

    // 4. Adaugare modul la curs
    public Modul adaugareModul(String titlu, String continut, int durata, int cursId) {
        Modul m = new Modul(0, titlu, continut, durata, cursId);
        modulRepo.save(m);
        audit.log("adaugare_modul");
        return m;
    }

    // 5. Vizualizare cursuri (cu filtrare optionala dupa categorie)
    public List<Curs> getCursuri(String categorie) {
        audit.log("vizualizare_cursuri");
        if (categorie == null || categorie.isBlank()) return cursRepo.findAll();
        return cursRepo.findByCategorie(categorie);
    }

    // 6. Cursuri la care e inscris un student
    public List<Curs> getCursuriStudent(int studentId) {
        audit.log("vizualizare_cursuri_student");
        return inscriereRepo.findByStudentId(studentId).stream()
            .map(i -> cursRepo.findById(i.getCursId()).orElse(null))
            .filter(c -> c != null)
            .toList();
    }

    // 7. Sustinere quiz
    public double sustineQuiz(int cursId, int studentId, List<Integer> raspunsuri) {
        Quiz quiz = quizRepo.findByCursId(cursId)
            .orElseThrow(() -> new RuntimeException("Quiz negasit pentru cursId=" + cursId));
        List<Intrebare> intrebari = quiz.getIntrebari();
        int corecte = 0;
        for (int i = 0; i < intrebari.size(); i++) {
            if (i < raspunsuri.size() && intrebari.get(i).esteCorect(raspunsuri.get(i))) {
                corecte++;
            }
        }
        double scor = intrebari.isEmpty() ? 0 :
            (double) corecte / intrebari.size() * quiz.getPunctajMaxim();
        if (studentId > 0) {
            inscriereRepo.updateScorQuiz(studentId, cursId, scor);
        }
        audit.log("sustinere_quiz");
        return scor;
    }

    // 8. Vizualizare progres student la un curs
    public Optional<Inscriere> getProgres(int studentId, int cursId) {
        audit.log("vizualizare_progres");
        return inscriereRepo.findByStudentAndCurs(studentId, cursId);
    }

    // 9. Studenti inscrisi la un curs
    public List<Student> getStudentiLaCurs(int cursId) {
        audit.log("vizualizare_studenti_curs");
        return inscriereRepo.findByCursId(cursId).stream()
            .map(i -> studentRepo.findById(i.getStudentId()).orElse(null))
            .filter(s -> s != null)
            .toList();
    }

    // 10. Stergere curs
    public void stergeCurs(int cursId) {
        cursRepo.delete(cursId);
        audit.log("stergere_curs");
    }

    // Actualizare nota
    public Inscriere actualizeazaNota(int inscriereId, double nota) {
        List<Inscriere> all = inscriereRepo.findAll();
        Inscriere ins = all.stream().filter(i -> i.getId() == inscriereId).findFirst()
            .orElseThrow(() -> new RuntimeException("Inscriere negasita: " + inscriereId));
        ins.setNotaFinala(nota);
        inscriereRepo.update(ins);
        audit.log("actualizare_nota");
        return ins;
    }

    // Adaugare quiz
    public Quiz adaugaQuiz(int cursId, int punctajMaxim, List<Intrebare> intrebari) {
        Quiz q = new Quiz(0, cursId, intrebari, punctajMaxim);
        quizRepo.save(q);
        audit.log("adaugare_quiz");
        return q;
    }

    // Getters pentru controllere
    public List<Student> getStudenti() { return studentRepo.findAll(); }
    public Optional<Student> getStudent(int id) { return studentRepo.findById(id); }
    public void updateStudent(Student s) { studentRepo.update(s); }
    public void deleteStudent(int id) { studentRepo.delete(id); }

    public List<Instructor> getInstructori() { return instructorRepo.findAll(); }
    public Instructor saveInstructor(Instructor i) { return instructorRepo.save(i); }

    public Optional<Curs> getCurs(int id) { return cursRepo.findById(id); }
    public void deleteCurs(int id) { stergeCurs(id); }

    public List<Inscriere> getInscrieri(int studentId) { return inscriereRepo.findByStudentId(studentId); }
    public List<Inscriere> getInscrieriCurs(int cursId) { return inscriereRepo.findByCursId(cursId); }

    public Optional<Quiz> getQuizByCurs(int cursId) { return quizRepo.findByCursId(cursId); }

    public List<Modul> getModuleByCurs(int cursId) { return modulRepo.findByCursId(cursId); }
}
