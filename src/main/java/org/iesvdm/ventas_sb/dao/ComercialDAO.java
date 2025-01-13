package org.iesvdm.ventas_sb.dao;

import org.iesvdm.ventas_sb.modelo.Comercial;

import java.util.List;
import java.util.Optional;

public interface ComercialDAO {
    void create(Comercial comercial) throws Exception;
    List<Comercial> getAll();
    Optional<Comercial> find(int id);
    void update(Comercial comercial);
    void delete(int id);

}
