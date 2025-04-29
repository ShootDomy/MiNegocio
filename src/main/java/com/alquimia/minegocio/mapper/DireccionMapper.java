package com.alquimia.minegocio.mapper;

import com.alquimia.minegocio.dto.DireccionDto;
import com.alquimia.minegocio.entity.Cliente;
import com.alquimia.minegocio.entity.Direccion;

public class DireccionMapper {
    public static DireccionDto mapToDireccionDto(Direccion direccion) {
        DireccionDto dto = new DireccionDto();
        dto.setDirId(direccion.getDirId());
        dto.setDirProvincia(direccion.getDirProvincia());
        dto.setDirCiudad(direccion.getDirCiudad());
        dto.setDirdireccion(direccion.getDirdireccion());
        dto.setDirMatriz(direccion.getDirMatriz());

        dto.setCliId(direccion.getCliente() != null ? direccion.getCliente().getCliId() : null);

        return dto;
    }

    public static Direccion mapToDireccion(DireccionDto direccionDto) {
        Direccion direccion = new Direccion();
        direccion.setDirId(direccionDto.getDirId());
        direccion.setDirProvincia(direccionDto.getDirProvincia());
        direccion.setDirCiudad(direccionDto.getDirCiudad());
        direccion.setDirdireccion(direccionDto.getDirdireccion());
        direccion.setDirMatriz(direccionDto.getDirMatriz());

        if (direccionDto.getCliId() != null) {
            Cliente cliente = new Cliente();
            cliente.setCliId(direccionDto.getCliId());
            direccion.setCliente(cliente);
        }

        return direccion;
    }
}
