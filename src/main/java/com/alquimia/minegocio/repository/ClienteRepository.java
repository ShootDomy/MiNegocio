package com.alquimia.minegocio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alquimia.minegocio.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    /**
     * OBTENER CLIENTES FILTRADOS POR NOMBRE O IDENTIFICACION
     */
    @Query(value = "SELECT cli_id, cli_tipo_identificacion, cli_num_identificacion, " +
            "cli_nombre, cli_correo, cli_telefono " +
            "FROM cliente " +
            "WHERE (cli_num_identificacion || '-' || UPPER(cli_nombre)) LIKE :buscar " +
            "ORDER BY cli_num_identificacion", nativeQuery = true)
    List<Cliente> obtenerClienteNomIden(@Param("buscar") String buscar);

    /**
     * OBTENER CLIENTES FILTRADOS POR IDENTIFICACION
     */
    @Query(value = "SELECT cli_id, cli_tipo_identificacion, cli_num_identificacion, " +
            "cli_nombre, cli_correo, cli_telefono " +
            "FROM cliente " +
            "WHERE cli_num_identificacion = :buscar " +
            "ORDER BY cli_num_identificacion", nativeQuery = true)
    List<Cliente> obtenerClienteByIdentificacion(@Param("buscar") String buscar);
}
