package com.elearning.controller;

import com.elearning.model.Instructor;
import com.elearning.service.ELearningService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/instructori")
public class InstructorController {

    private final ELearningService service;

    public InstructorController(ELearningService service) {
        this.service = service;
    }

    @GetMapping
    public List<Instructor> getAll() {
        return service.getInstructori();
    }

    @PostMapping
    public Instructor create(@RequestBody Map<String, String> body) {
        Instructor i = new Instructor(0,
            body.get("nume"), body.get("email"), body.get("parola"), body.get("specializare"));
        return service.saveInstructor(i);
    }
}
