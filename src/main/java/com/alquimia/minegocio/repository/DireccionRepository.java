package com.alquimia.minegocio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alquimia.minegocio.entity.Direccion;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Long> {
    /**
     * OBTENER LAS DIRECCIONES FILTRANDO POR CLIENTE
     * 
     * SE ORDENA DE MANERA ASCENDENTE COMO PRIMER RESULTADO LA DIRECCION MATRIZ Y
     * LUEGO LAS DEMAS
     */
    @Query(value = "SELECT dir_id, dir_provincia, dir_ciudad,\n" +
            "\tdir_matriz, dir_direccion, cli_id\n" +
            "FROM direccion\n" +
            "WHERE cli_id = :buscar\n" +
            "ORDER BY CASE WHEN dir_matriz IS TRUE THEN 1 ELSE 2 END, \n" +
            "\tdir_provincia, dir_ciudad, dir_direccion ASC;", nativeQuery = true)
    List<Direccion> obtenerDireccionByCliente(@Param("buscar") Long buscar);
}
