package com.elearning.repository;

import com.elearning.model.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepository {

    private final JdbcTemplate jdbc;

    public StudentRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<Student> mapper = (rs, row) -> {
        Student s = new Student(
            rs.getInt("id"),
            rs.getString("nume"),
            rs.getString("email"),
            rs.getString("parola")
        );
        return s;
    };

    public List<Student> findAll() {
        return jdbc.query("SELECT * FROM utilizatori WHERE rol='STUDENT' ORDER BY nume", mapper);
    }

    public Optional<Student> findById(int id) {
        List<Student> result = jdbc.query(
            "SELECT * FROM utilizatori WHERE id=? AND rol='STUDENT'", mapper, id);
        return result.stream().findFirst();
    }

    public Student save(Student s) {
        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update(con -> {
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO utilizatori(nume,email,parola,rol) VALUES(?,?,?,'STUDENT')",
                new String[]{"id"});
            ps.setString(1, s.getNume());
            ps.setString(2, s.getEmail());
            ps.setString(3, s.getParola());
            return ps;
        }, kh);
        s.setId(kh.getKey().intValue());
        return s;
    }

    public void update(Student s) {
        jdbc.update("UPDATE utilizatori SET nume=?, email=?, parola=? WHERE id=? AND rol='STUDENT'",
            s.getNume(), s.getEmail(), s.getParola(), s.getId());
    }

    public void delete(int id) {
        jdbc.update("DELETE FROM utilizatori WHERE id=? AND rol='STUDENT'", id);
    }
}
