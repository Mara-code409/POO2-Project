package com.elearning.repository;

import com.elearning.model.Curs;
import com.elearning.model.Instructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class CursRepository {

    private final JdbcTemplate jdbc;

    public CursRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<Curs> mapper = (rs, row) -> {
        Instructor instructor = new Instructor(
            rs.getInt("instructor_id"),
            rs.getString("instructor_nume"),
            rs.getString("instructor_email"),
            "",
            rs.getString("instructor_specializare")
        );
        return new Curs(
            rs.getInt("id"),
            rs.getString("titlu"),
            rs.getString("descriere"),
            instructor,
            rs.getString("categorie"),
            rs.getDouble("pret")
        );
    };

    private static final String SELECT_WITH_INSTRUCTOR =
        "SELECT c.*, u.nume AS instructor_nume, u.email AS instructor_email, u.specializare AS instructor_specializare " +
        "FROM cursuri c JOIN utilizatori u ON c.instructor_id = u.id ";

    public List<Curs> findAll() {
        return jdbc.query(SELECT_WITH_INSTRUCTOR + "ORDER BY c.titlu", mapper);
    }

    public Optional<Curs> findById(int id) {
        List<Curs> result = jdbc.query(SELECT_WITH_INSTRUCTOR + "WHERE c.id=?", mapper, id);
        return result.stream().findFirst();
    }

    public List<Curs> findByCategorie(String categorie) {
        return jdbc.query(SELECT_WITH_INSTRUCTOR + "WHERE LOWER(c.categorie)=LOWER(?) ORDER BY c.titlu",
            mapper, categorie);
    }

    public List<Curs> findByInstructorId(int instructorId) {
        return jdbc.query(SELECT_WITH_INSTRUCTOR + "WHERE c.instructor_id=? ORDER BY c.titlu",
            mapper, instructorId);
    }

    public Curs save(Curs c) {
        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update(con -> {
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO cursuri(titlu,descriere,instructor_id,categorie,pret) VALUES(?,?,?,?,?)",
                new String[]{"id"});
            ps.setString(1, c.getTitlu());
            ps.setString(2, c.getDescriere());
            ps.setInt(3, c.getInstructor().getId());
            ps.setString(4, c.getCategorie());
            ps.setDouble(5, c.getPret());
            return ps;
        }, kh);
        c.setId(kh.getKey().intValue());
        return c;
    }

    public void update(Curs c) {
        jdbc.update("UPDATE cursuri SET titlu=?, descriere=?, categorie=?, pret=? WHERE id=?",
            c.getTitlu(), c.getDescriere(), c.getCategorie(), c.getPret(), c.getId());
    }

    public void delete(int id) {
        jdbc.update("DELETE FROM cursuri WHERE id=?", id);
    }
}
