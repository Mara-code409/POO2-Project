package com.elearning.repository;

import com.elearning.model.Modul;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class ModulRepository {

    private final JdbcTemplate jdbc;

    public ModulRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<Modul> mapper = (rs, row) -> new Modul(
        rs.getInt("id"),
        rs.getString("titlu"),
        rs.getString("continut"),
        rs.getInt("durata_minute"),
        rs.getInt("curs_id")
    );

    public List<Modul> findByCursId(int cursId) {
        return jdbc.query("SELECT * FROM module WHERE curs_id=? ORDER BY id", mapper, cursId);
    }

    public Modul save(Modul m) {
        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update(con -> {
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO module(titlu,continut,durata_minute,curs_id) VALUES(?,?,?,?)",
                new String[]{"id"});
            ps.setString(1, m.getTitlu());
            ps.setString(2, m.getContinut());
            ps.setInt(3, m.getDurata());
            ps.setInt(4, m.getCursId());
            return ps;
        }, kh);
        m.setId(kh.getKey().intValue());
        return m;
    }

    public void delete(int id) {
        jdbc.update("DELETE FROM module WHERE id=?", id);
    }
}
