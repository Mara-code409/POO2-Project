package com.elearning.repository;

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
public class InstructorRepository {

    private final JdbcTemplate jdbc;

    public InstructorRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<Instructor> mapper = (rs, row) -> new Instructor(
        rs.getInt("id"),
        rs.getString("nume"),
        rs.getString("email"),
        rs.getString("parola"),
        rs.getString("specializare")
    );

    public List<Instructor> findAll() {
        return jdbc.query("SELECT * FROM utilizatori WHERE rol='INSTRUCTOR' ORDER BY nume", mapper);
    }

    public Optional<Instructor> findById(int id) {
        List<Instructor> result = jdbc.query(
            "SELECT * FROM utilizatori WHERE id=? AND rol='INSTRUCTOR'", mapper, id);
        return result.stream().findFirst();
    }

    public Instructor save(Instructor i) {
        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update(con -> {
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO utilizatori(nume,email,parola,rol,specializare) VALUES(?,?,?,'INSTRUCTOR',?)",
                new String[]{"id"});
            ps.setString(1, i.getNume());
            ps.setString(2, i.getEmail());
            ps.setString(3, i.getParola());
            ps.setString(4, i.getSpecializare());
            return ps;
        }, kh);
        i.setId(kh.getKey().intValue());
        return i;
    }

    public void update(Instructor i) {
        jdbc.update("UPDATE utilizatori SET nume=?, email=?, specializare=? WHERE id=? AND rol='INSTRUCTOR'",
            i.getNume(), i.getEmail(), i.getSpecializare(), i.getId());
    }

    public void delete(int id) {
        jdbc.update("DELETE FROM utilizatori WHERE id=? AND rol='INSTRUCTOR'", id);
    }
}
