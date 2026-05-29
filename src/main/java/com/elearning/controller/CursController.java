package com.elearning.controller;

import com.elearning.model.Curs;
import com.elearning.model.Modul;
import com.elearning.model.Student;
import com.elearning.service.ELearningService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cursuri")
public class CursController {

    private final ELearningService service;

    public CursController(ELearningService service) {
        this.service = service;
    }

    @GetMapping
    public List<Curs> getAll(@RequestParam(required = false) String categorie) {
        return service.getCursuri(categorie);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curs> getById(@PathVariable int id) {
        return service.getCurs(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/module")
    public List<Modul> getModule(@PathVariable int id) {
        return service.getModuleByCurs(id);
    }

    @GetMapping("/{id}/studenti")
    public List<Student> getStudenti(@PathVariable int id) {
        return service.getStudentiLaCurs(id);
    }

    @PostMapping
    public Curs create(@RequestBody Map<String, Object> body) {
        return service.adaugareCurs(
            (String) body.get("titlu"),
            (String) body.get("descriere"),
            (Integer) body.get("instructorId"),
            (String) body.get("categorie"),
            ((Number) body.get("pret")).doubleValue()
        );
    }

    @PostMapping("/{id}/module")
    public Modul addModul(@PathVariable int id, @RequestBody Map<String, Object> body) {
        return service.adaugareModul(
            (String) body.get("titlu"),
            (String) body.get("continut"),
            (Integer) body.get("durata"),
            id
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.deleteCurs(id);
        return ResponseEntity.noContent().build();
    }
}
