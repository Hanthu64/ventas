package org.iesvdm.ventas_sb;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.ventas_sb.dao.ClienteDAO;
import org.iesvdm.ventas_sb.dao.ClienteDAOJDBCClientImpl;
import org.iesvdm.ventas_sb.modelo.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.nio.charset.Charset;
import java.sql.PreparedStatement;
import java.util.*;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JDBCClientTests {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    JdbcClient jdbcClient;

    Cliente cli1 = Cliente.builder()
            .nombre("Jose M.")
            .apellido1("Martín")
            .apellido2("Tejero")
            .ciudad("Málaga")
            .categoría(1)
            .build();

    Cliente cli2 = Cliente.builder()
            .nombre("María")
            .apellido1("Pérez")
            .apellido2("García")
            .ciudad("Granada")
            .categoría(2)
            .build();

    Cliente cli3 = Cliente.builder()
            .nombre("Javier")
            .apellido1("Gutiérrez")
            .apellido2("Martínez")
            .ciudad("Málaga")
            .categoría(3)
            .build();


    @Test
    void insertWithoutIDRecoveryTest() {

    }

    @Test
    void insertWithIDRecoveryTest() {

    }

    @Test
    void update() {

    }


    @Test
    void delete() {

    }


    @Test
    void batch() {

    }

    @Test
    void getAll() {
        String query = """
        SELECT * FROM cliente;
        """;

        RowMapper<Cliente> rowMapperCliente = (rs, rowNum) -> new Cliente(rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("apellido1"),
                rs.getString("apellido2"),
                rs.getString("ciudad"),
                rs.getInt("categoría")
        );

        List<Cliente> listaCli = jdbcClient.sql(query)
                                .query(rowMapperCliente)
                                .list();

        assertTrue(listaCli.size() > 0);
    }

    @Test
    void findById() {
        int idToFind = 1;

        String query = """
                SELECT * FROM cliente WHERE id = :id;
                """;

        Optional<Cliente> optCliente = jdbcClient.sql(query)
                .param("id", idToFind)
                .query(Cliente.class)
                .optional();

        assertTrue(optCliente != null);
    }

    //A realizar por el alumno...
    @Test
    void findByNombre() {
        String nombre = "Marcos";

        String query = """
                SELECT * FROM cliente WHERE nombre = :nombre;
                """;
        Optional<Cliente> optCliente = jdbcClient.sql(query)
                .param("nombre", nombre)
                .query(Cliente.class)
                .optional();

        assertTrue(optCliente != null);
    }

    @Test
    void findByNombreButNotFound() {
        String nombre = "Buenos días";

        String query = """
                SELECT * FROM cliente WHERE nombre = :nombre;
                """;
        Optional<Cliente> optCliente = jdbcClient.sql(query)
                .param("nombre", nombre)
                .query(Cliente.class)
                .optional();

        assertTrue(optCliente == null);
    }

    @Test
    void findClienteByCaracteristicaBetween() {
        int característicaInit = 0;
        int característicaFin = 0;
        //TODO
    }

    void findClienteByNombreContainingAndApellido1Containing() {
        String nombreContaining = "";
        String apellido1Containing = "";
        //TODO


    }

    void findClienteByNombreContainingAndApellido1ContainingButNotFound() {
        String nombreContaining = "";
        String apellido1Containing = "";
        //TODO


    }

    void findPedidosWithClienteAndComercialByCliente_id() {
        int clienteId = 0;
        //TODO
    }

    void insertNewClienteAndPedido() {
        //
    }

}
