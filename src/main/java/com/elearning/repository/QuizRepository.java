package com.elearning.repository;

import com.elearning.model.Intrebare;
import com.elearning.model.Quiz;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class QuizRepository {

    private final JdbcTemplate jdbc;

    public QuizRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Optional<Quiz> findByCursId(int cursId) {
        List<Quiz> quizuri = jdbc.query(
            "SELECT * FROM quizuri WHERE curs_id=?",
            (rs, row) -> new Quiz(rs.getInt("id"), rs.getInt("curs_id"), null, rs.getInt("punctaj_maxim")),
            cursId);
        if (quizuri.isEmpty()) return Optional.empty();
        Quiz q = quizuri.get(0);
        q.setIntrebari(findIntrebari(q.getId()));
        return Optional.of(q);
    }

    public Optional<Quiz> findById(int id) {
        List<Quiz> quizuri = jdbc.query(
            "SELECT * FROM quizuri WHERE id=?",
            (rs, row) -> new Quiz(rs.getInt("id"), rs.getInt("curs_id"), null, rs.getInt("punctaj_maxim")),
            id);
        if (quizuri.isEmpty()) return Optional.empty();
        Quiz q = quizuri.get(0);
        q.setIntrebari(findIntrebari(q.getId()));
        return Optional.of(q);
    }

    public Quiz save(Quiz q) {
        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update(con -> {
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO quizuri(curs_id,punctaj_maxim) VALUES(?,?)",
                new String[]{"id"});
            ps.setInt(1, q.getCursId());
            ps.setInt(2, q.getPunctajMaxim());
            return ps;
        }, kh);
        q.setId(kh.getKey().intValue());
        if (q.getIntrebari() != null) {
            for (Intrebare i : q.getIntrebari()) {
                i.setQuizId(q.getId());
                saveIntrebare(i);
            }
        }
        return q;
    }

    private void saveIntrebare(Intrebare i) {
        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update(con -> {
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO intrebari(text,variante_raspuns,index_raspuns_corect,quiz_id) VALUES(?,?,?,?)",
                new String[]{"id"});
            ps.setString(1, i.getText());
            ps.setString(2, String.join("||", i.getVarianteRaspuns()));
            ps.setInt(3, i.getIndexRaspunsCorect());
            ps.setInt(4, i.getQuizId());
            return ps;
        }, kh);
        i.setId(kh.getKey().intValue());
    }

    private List<Intrebare> findIntrebari(int quizId) {
        return jdbc.query(
            "SELECT * FROM intrebari WHERE quiz_id=? ORDER BY id",
            (rs, row) -> {
                List<String> variante = Arrays.asList(rs.getString("variante_raspuns").split("\\|\\|"));
                return new Intrebare(
                    rs.getInt("id"),
                    rs.getString("text"),
                    variante,
                    rs.getInt("index_raspuns_corect"),
                    rs.getInt("quiz_id")
                );
            }, quizId);
    }

    public void delete(int id) {
        jdbc.update("DELETE FROM quizuri WHERE id=?", id);
    }
}
