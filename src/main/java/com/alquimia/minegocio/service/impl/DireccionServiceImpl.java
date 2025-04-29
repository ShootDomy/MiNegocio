package com.alquimia.minegocio.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.alquimia.minegocio.dto.DireccionDto;
import com.alquimia.minegocio.entity.Direccion;
import com.alquimia.minegocio.exception.BusinessException;
import com.alquimia.minegocio.exception.ResourceNotFoundException;
import com.alquimia.minegocio.exception.ValidationException;
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

      // TODO: VALIDACIONES

      // FIXME: VALIDACION PARA NO CREAR MAS DE UNA DIRECCION MATRIZ
      List<Direccion> dirMatriz = direccionRepository
          .obtenerDireccionMatrizCliente(direccion.getCliente().getCliId());

      if (dirMatriz != null && !dirMatriz.isEmpty()) {
        throw new BusinessException("EL CLIENTE YA TIENE UNA DIRECCION MATRIZ REGISTRADA");
      }

      Direccion guardar = direccionRepository.save(direccion);

      return DireccionMapper.mapToDireccionDto(guardar);
    } catch (ValidationException ve) {
      System.err.println("ERROR DE VALIDACIÓN=>" + ve);
      throw new ValidationException(ve.getMessage());
    } catch (ResourceNotFoundException re) {
      System.err.println("RECURSO NO ENCONTRADO=>" + re);
      throw new ResourceNotFoundException(re.getMessage());
    } catch (BusinessException bu) {
      System.err.println("ERROR GENERAL=>" + bu);
      throw new BusinessException(bu.getMessage());
    } catch (Exception e) {
      throw new BusinessException("ERROR AL CREAR LA DIRECCIÓN");
    }
  }

  @Override
  public DireccionDto getDireccionById(Long dirId) {
    try {

      Direccion direccion = direccionRepository.findById(dirId)
          .orElseThrow(() -> new ResourceNotFoundException("NO SE HA ENCONTRADO UNA DIRECION CON ESE ID"));
      return DireccionMapper.mapToDireccionDto(direccion);
    } catch (ValidationException ve) {
      System.err.println("ERROR DE VALIDACIÓN=>" + ve);
      throw new ValidationException(ve.getMessage());
    } catch (ResourceNotFoundException re) {
      System.err.println("RECURSO NO ENCONTRADO=>" + re);
      throw new ResourceNotFoundException(re.getMessage());
    } catch (BusinessException bu) {
      System.err.println("ERROR GENERAL=>" + bu);
      throw new BusinessException(bu.getMessage());
    } catch (Exception e) {
      throw new BusinessException("ERROR AL OBTENER LA DIRECCION POR ID");
    }
  }

  @Override
  public List<DireccionDto> getAllDireccion() {
    try {
      List<Direccion> direcciones = direccionRepository.findAll();

      return direcciones.stream().map((direccion) -> DireccionMapper.mapToDireccionDto(direccion))
          .collect((Collectors.toList()));
    } catch (ValidationException ve) {
      System.err.println("ERROR DE VALIDACIÓN=>" + ve);
      throw new ValidationException(ve.getMessage());
    } catch (ResourceNotFoundException re) {
      System.err.println("RECURSO NO ENCONTRADO=>" + re);
      throw new ResourceNotFoundException(re.getMessage());
    } catch (BusinessException bu) {
      System.err.println("ERROR GENERAL=>" + bu);
      throw new BusinessException(bu.getMessage());
    } catch (Exception e) {
      throw new BusinessException("ERROR AL OBTENER TODAS LAS DIRECCIONES");
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
    } catch (ValidationException ve) {
      System.err.println("ERROR DE VALIDACIÓN=>" + ve);
      throw new ValidationException(ve.getMessage());
    } catch (ResourceNotFoundException re) {
      System.err.println("RECURSO NO ENCONTRADO=>" + re);
      throw new ResourceNotFoundException(re.getMessage());
    } catch (BusinessException bu) {
      System.err.println("ERROR GENERAL=>" + bu);
      throw new BusinessException(bu.getMessage());
    } catch (Exception e) {
      throw new BusinessException("ERROR AL ACTUALIZAR LA DIRECCIÓN");
    }
  }

  @Override
  public void deleteDireccionById(Long dirId) {
    try {
      direccionRepository.findById(dirId)
          .orElseThrow(() -> new ResourceNotFoundException("NO SE HA ENCONTRADO UNA DIRECION CON ESE ID"));

      direccionRepository.deleteById(dirId);
    } catch (ValidationException ve) {
      System.err.println("ERROR DE VALIDACIÓN=>" + ve);
      throw new ValidationException(ve.getMessage());
    } catch (ResourceNotFoundException re) {
      System.err.println("RECURSO NO ENCONTRADO=>" + re);
      throw new ResourceNotFoundException(re.getMessage());
    } catch (BusinessException bu) {
      System.err.println("ERROR GENERAL=>" + bu);
      throw new BusinessException(bu.getMessage());
    } catch (Exception e) {
      throw new BusinessException("ERROR AL ELIMINAR LA DIRECCIÓN");
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

    } catch (ValidationException ve) {
      System.err.println("ERROR DE VALIDACIÓN=>" + ve);
      throw new ValidationException(ve.getMessage());
    } catch (ResourceNotFoundException re) {
      System.err.println("RECURSO NO ENCONTRADO=>" + re);
      throw new ResourceNotFoundException(re.getMessage());
    } catch (BusinessException bu) {
      System.err.println("ERROR GENERAL=>" + bu);
      throw new BusinessException(bu.getMessage());
    } catch (Exception e) {
      throw new BusinessException("ERROR AL OBTENER LAS DIRECCIONES POR CLIENTE");
    }
  }
}
