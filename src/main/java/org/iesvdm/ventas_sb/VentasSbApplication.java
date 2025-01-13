package org.iesvdm.ventas_sb;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.ventas_sb.dao.ComercialDAOJDBCClientImpl;
import org.iesvdm.ventas_sb.dao.ComercialDAOJDBCTemplateImpl;
import org.iesvdm.ventas_sb.modelo.Comercial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class VentasSbApplication implements CommandLineRunner {

    @Autowired
    private ComercialDAOJDBCClientImpl comDAO;

    public static void main(String[] args) {
        SpringApplication.run(VentasSbApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception{
        log.info("Arranca la aplicación");
        log.info("Prueba ComercialDao...");

        var com1 = Comercial.builder().nombre("José M.")
                                      .apellido1("Martín")
                                      .apellido2("Tejero")
                                      .comision(1.5f)
                                      .build();

        comDAO.create(com1);
        System.out.println(com1);

        var listCo = comDAO.getAll();
        System.out.println(listCo);

        System.out.println();
        System.out.println(comDAO.find(2));

        com1.setComision(2.5f);
        comDAO.update(com1);

        var optComUpdated = comDAO.find(com1.getId());
        System.out.println(optComUpdated);
    }
}
