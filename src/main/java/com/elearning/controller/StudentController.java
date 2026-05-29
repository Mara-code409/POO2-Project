package com.elearning.controller;

import com.elearning.model.Student;
import com.elearning.service.ELearningService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/studenti")
public class StudentController {

    private final ELearningService service;

    public StudentController(ELearningService service) {
        this.service = service;
    }

    @GetMapping
    public List<Student> getAll() {
        return service.getStudenti();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable int id) {
        return service.getStudent(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Student create(@RequestBody Map<String, String> body) {
        return service.inregistrareStudent(
            body.get("nume"), body.get("email"), body.get("parola"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody Map<String, String> body) {
        return service.getStudent(id).map(s -> {
            if (body.containsKey("nume")) s.setNume(body.get("nume"));
            if (body.containsKey("email")) s.setEmail(body.get("email"));
            service.updateStudent(s);
            return ResponseEntity.ok().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
