package org.iesvdm.ventas_sb.dao;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.ventas_sb.modelo.Comercial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Repository
public class ComercialDAOJDBCTemplateImpl implements ComercialDAO{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void create(Comercial comercial) throws Exception{

        if(Objects.isNull(comercial.getId())){

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        int rowsUpdated = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("""
            INSERT INTO comercial (nombre, apellido1, apellido2, comisión) VALUES (?, ?, ?, ?)
            """, new String[]{"id"});
            int idx = 1;
            ps.setString(idx++, comercial.getNombre());
            ps.setString(idx++, comercial.getApellido1());
            ps.setString(idx++, comercial.getApellido2());
            ps.setFloat(idx++, comercial.getComision());
            return ps;
        }, keyHolder);

        log.info("Filas insertadas{}", rowsUpdated);
        }else{
            throw new Exception("Comercial con id null no puede crearse");
        }

    }
    @Override
    public List<Comercial> getAll() {
        List<Comercial> listCo = jdbcTemplate.query("SELECT * FROM comercial",
                BeanPropertyRowMapper.newInstance(Comercial.class));
        return listCo;
    }

    @Override
    public Optional<Comercial> find(int id) {

        Optional<Comercial> optCom = jdbcTemplate.query("""
                SELECT * FROM comercial WHERE id = ?
                """, rs -> {
            if (rs.next()){
                return Optional.of(UtilDAO.buildComercial(rs));
            }else{
                return Optional.empty();
            }
        }, id);
        return optCom;
    }

    @Override
    public void update(Comercial comercial) {
        int rowsUpdated = jdbcTemplate.update("UPDATE comercial SET nombre = ?, apellido1 = ?, apellido2 = ?, comisión = ? WHERE id = ?",
                comercial.getNombre(),
                comercial.getApellido1(),
                comercial.getApellido2(),
                comercial.getComision(),
                comercial.getId());

        log.info("Número de filas actualizadas: {}", rowsUpdated);
    }

    @Override
    public void delete(int id) {
        int rowsDeleted = jdbcTemplate.update("DELETE FROM comercial WHERE id = ?",
                id);

        log.info("Número de filas borradas: {}", rowsDeleted);
    }
}
