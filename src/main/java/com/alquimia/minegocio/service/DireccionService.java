package com.alquimia.minegocio.service;

import java.util.List;

import com.alquimia.minegocio.dto.DireccionDto;

public interface DireccionService {
    DireccionDto createDireccion(DireccionDto direccionDto);

    DireccionDto getDireccionById(Long cliId);

    List<DireccionDto> getAllDireccion();

    DireccionDto updateDireccion(Long cliId, DireccionDto direccionDto);

    void deleteDireccionById(Long cliId);

    List<DireccionDto> obtenerDireccionByCliente(Long buscar);
}
