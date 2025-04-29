package com.alquimia.minegocio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alquimia.minegocio.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
