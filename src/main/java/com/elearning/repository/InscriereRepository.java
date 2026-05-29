package com.elearning.repository;

import com.elearning.model.Inscriere;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class InscriereRepository {

    private final JdbcTemplate jdbc;

    public InscriereRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<Inscriere> mapper = (rs, row) -> {
        Inscriere i = new Inscriere();
        i.setId(rs.getInt("id"));
        i.setStudentId(rs.getInt("student_id"));
        i.setCursId(rs.getInt("curs_id"));
        i.setDataInscriere(rs.getDate("data_inscriere").toLocalDate());
        i.setProgres(rs.getDouble("progres"));
        i.setNotaFinala(rs.getDouble("nota_finala"));
        i.setScorQuiz(rs.getDouble("scor_quiz"));
        return i;
    };

    public List<Inscriere> findAll() {
        return jdbc.query("SELECT * FROM inscrieri", mapper);
    }

    public List<Inscriere> findByStudentId(int studentId) {
        return jdbc.query("SELECT * FROM inscrieri WHERE student_id=?", mapper, studentId);
    }

    public List<Inscriere> findByCursId(int cursId) {
        return jdbc.query("SELECT * FROM inscrieri WHERE curs_id=?", mapper, cursId);
    }

    public Optional<Inscriere> findByStudentAndCurs(int studentId, int cursId) {
        List<Inscriere> result = jdbc.query(
            "SELECT * FROM inscrieri WHERE student_id=? AND curs_id=?", mapper, studentId, cursId);
        return result.stream().findFirst();
    }

    public Inscriere save(Inscriere i) {
        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update(con -> {
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO inscrieri(student_id,curs_id,data_inscriere,progres,nota_finala) VALUES(?,?,?,?,?)",
                new String[]{"id"});
            ps.setInt(1, i.getStudentId());
            ps.setInt(2, i.getCursId());
            ps.setDate(3, Date.valueOf(i.getDataInscriere()));
            ps.setDouble(4, i.getProgres());
            ps.setDouble(5, i.getNotaFinala());
            return ps;
        }, kh);
        i.setId(kh.getKey().intValue());
        return i;
    }

    public void update(Inscriere i) {
        jdbc.update("UPDATE inscrieri SET progres=?, nota_finala=?, scor_quiz=? WHERE id=?",
            i.getProgres(), i.getNotaFinala(), i.getScorQuiz(), i.getId());
    }

    public void updateScorQuiz(int studentId, int cursId, double scor) {
        jdbc.update("UPDATE inscrieri SET scor_quiz=? WHERE student_id=? AND curs_id=?",
            scor, studentId, cursId);
    }

    public void delete(int id) {
        jdbc.update("DELETE FROM inscrieri WHERE id=?", id);
    }
}
