package com.elearning.controller;

import com.elearning.model.Inscriere;
import com.elearning.service.ELearningService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/inscrieri")
public class InscriereController {

    private final ELearningService service;

    public InscriereController(ELearningService service) {
        this.service = service;
    }

    @PostMapping
    public Inscriere inscriere(@RequestBody Map<String, Integer> body) {
        return service.inscriere(body.get("studentId"), body.get("cursId"));
    }

    @GetMapping("/student/{studentId}")
    public List<Inscriere> byStudent(@PathVariable int studentId) {
        return service.getInscrieri(studentId);
    }

    @GetMapping("/curs/{cursId}")
    public List<Inscriere> byCurs(@PathVariable int cursId) {
        return service.getInscrieriCurs(cursId);
    }

    @PutMapping("/{id}/nota")
    public Inscriere actualizeazaNota(@PathVariable int id, @RequestBody Map<String, Double> body) {
        return service.actualizeazaNota(id, body.get("nota"));
    }
}
