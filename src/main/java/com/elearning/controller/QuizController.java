package com.elearning.controller;

import com.elearning.model.Intrebare;
import com.elearning.model.Quiz;
import com.elearning.service.ELearningService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/quizuri")
public class QuizController {

    private final ELearningService service;

    public QuizController(ELearningService service) {
        this.service = service;
    }

    @GetMapping("/{cursId}")
    public ResponseEntity<Quiz> getByCurs(@PathVariable int cursId) {
        return service.getQuizByCurs(cursId)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{cursId}")
    public Quiz createQuiz(@PathVariable int cursId, @RequestBody Map<String, Object> body) {
        int punctajMaxim = (Integer) body.get("punctajMaxim");
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> rawIntrebari = (List<Map<String, Object>>) body.get("intrebari");
        List<Intrebare> intrebari = rawIntrebari.stream().map(r -> {
            @SuppressWarnings("unchecked")
            List<String> variante = (List<String>) r.get("varianteRaspuns");
            return new Intrebare(0, (String) r.get("text"), variante,
                (Integer) r.get("indexRaspunsCorect"), 0);
        }).toList();
        return service.adaugaQuiz(cursId, punctajMaxim, intrebari);
    }

    @PostMapping("/{cursId}/sustine")
    public Map<String, Object> sustine(@PathVariable int cursId, @RequestBody Map<String, Object> body) {
        @SuppressWarnings("unchecked")
        List<Integer> raspunsuri = (List<Integer>) body.get("raspunsuri");
        int studentId = body.containsKey("studentId") ? (Integer) body.get("studentId") : 0;
        double scor = service.sustineQuiz(cursId, studentId, raspunsuri);
        Quiz quiz = service.getQuizByCurs(cursId).orElseThrow();
        return Map.of("scor", scor, "punctajMaxim", quiz.getPunctajMaxim());
    }
}
