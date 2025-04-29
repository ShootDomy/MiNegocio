package com.alquimia.minegocio.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.alquimia.minegocio.dto.DireccionDto;
import com.alquimia.minegocio.entity.Direccion;
import com.alquimia.minegocio.exception.ResourceNotFoundException;
import com.alquimia.minegocio.mapper.DireccionMapper;
import com.alquimia.minegocio.repository.DireccionRepository;
import com.alquimia.minegocio.service.DireccionService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DireccionServiceImpl implements DireccionService {
    private DireccionRepository direccionRepository;

    @Override
    public DireccionDto createDireccion(DireccionDto direccionDto) {
        try {
            Direccion direccion = DireccionMapper.mapToDireccion(direccionDto);
            Direccion guardar = direccionRepository.save(direccion);

            return DireccionMapper.mapToDireccionDto(guardar);
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'createdireccion'");
        }
    }

    @Override
    public DireccionDto getDireccionById(Long dirId) {
        try {

            Direccion direccion = direccionRepository.findById(dirId)
                    .orElseThrow(() -> new ResourceNotFoundException("NO SE HA ENCONTRADO UNA DIRECION CON ESE ID"));
            return DireccionMapper.mapToDireccionDto(direccion);
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'getdireccionById'");
        }
    }

    @Override
    public List<DireccionDto> getAllDireccion() {
        try {
            List<Direccion> direcciones = direccionRepository.findAll();

            return direcciones.stream().map((direccion) -> DireccionMapper.mapToDireccionDto(direccion))
                    .collect((Collectors.toList()));
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'getAlldireccion'");
        }
    }

    @Override
    public DireccionDto updateDireccion(Long dirId, DireccionDto direccionDto) {
        try {
            Direccion direccion = direccionRepository.findById(dirId)
                    .orElseThrow(() -> new ResourceNotFoundException("NO SE HA ENCONTRADO UNA DIRECION CON ESE ID"));

            direccion.setDirProvincia(direccionDto.getDirProvincia());
            direccion.setDirCiudad(direccionDto.getDirCiudad());
            direccion.setDirdireccion(direccionDto.getDirdireccion());
            direccion.setDirMatriz(direccionDto.getDirMatriz());
            // direccion.setCliId(direccionDto.getCliId());

            Direccion actualizar = direccionRepository.save(direccion);

            return DireccionMapper.mapToDireccionDto(actualizar);
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'updatedireccion'");
        }
    }

    @Override
    public void deleteDireccionById(Long dirId) {
        try {
            direccionRepository.findById(dirId)
                    .orElseThrow(() -> new ResourceNotFoundException("NO SE HA ENCONTRADO UNA DIRECION CON ESE ID"));

            direccionRepository.deleteById(dirId);
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'deletedireccionById'");
        }
    }

    @Override
    public List<DireccionDto> obtenerDireccionByCliente(Long buscar) {
        try {
            List<Direccion> direcciones = direccionRepository.obtenerDireccionByCliente(buscar);

            System.out.println("buscar ==>>" + buscar);
            System.out.println("clientes ==>>" + direcciones);

            return direcciones.stream().map((direccion) -> DireccionMapper.mapToDireccionDto(direccion))
                    .collect((Collectors.toList()));

        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'obtenerDireccionByCliente'");
        }
    }
}
