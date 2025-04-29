package com.alquimia.minegocio.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.alquimia.minegocio.dto.ClienteDto;
import com.alquimia.minegocio.entity.Cliente;
import com.alquimia.minegocio.entity.Direccion;
import com.alquimia.minegocio.exception.BusinessException;
import com.alquimia.minegocio.exception.ResourceNotFoundException;
import com.alquimia.minegocio.exception.ValidationException;
import com.alquimia.minegocio.mapper.ClienteMapper;
import com.alquimia.minegocio.mapper.DireccionMapper;
import com.alquimia.minegocio.repository.ClienteRepository;
import com.alquimia.minegocio.service.ClienteService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClienteServiceImpl implements ClienteService {
  private ClienteRepository clienteRepository;

  // @Override
  // public ClienteDto createCliente(ClienteDto clienteDto) {
  // try {
  // Cliente cliente = ClienteMapper.mapToCliente(clienteDto);
  // Cliente guardar = clienteRepository.save(cliente);

  // return ClienteMapper.mapToClienteDto(guardar);
  // } catch (Exception e) {
  // throw new UnsupportedOperationException("Unimplemented method
  // 'createCliente'");
  // }
  // }

  @Override
  public ClienteDto createCliente(ClienteDto clienteDto) {
    try {
      Cliente cliente = ClienteMapper.mapToCliente(clienteDto);

      // TODO: VALIDACIONES PARA LA IDENTIFICACION
      // FIXME: VALIDACIÓN LONGITUD
      if ("CEDULA".equals(cliente.getCliTipoIdentificacion()) &&
          cliente.getCliNumIdentificacion().length() != 10) {

        throw new ValidationException("LA CÉDULA DEBE TENER 10 CARACTERES");
      }

      if ("RUC".equals(cliente.getCliTipoIdentificacion()) &&
          cliente.getCliNumIdentificacion().length() != 13) {
        throw new ValidationException("EL RUC DEBE TENER 13 CARACTERES");
      }

      // FIXME: VALIDACION PARA EVITAR CLIENTES REPETIDOS
      List<Cliente> cliRepetido = clienteRepository
          .obtenerClienteByIdentificacion(cliente.getCliNumIdentificacion());

      if (cliRepetido != null && !cliRepetido.isEmpty()) {
        throw new BusinessException("LA IDENTIFICACIÓN YA ESTÁ REGISTRADA");
      }

      // FIXME: VALIDACIONES PARA NUMERO DE TELEFONO
      if (cliente.getCliTelefono().length() != 10) {
        throw new ValidationException("EL NÚMERO DE TELÉFONO DEBE CONTENER 10 CARACTERES");
      }

      for (Direccion direccion : cliente.getDirecciones()) {
        direccion.setCliente(cliente);
      }

      Cliente guardado = clienteRepository.save(cliente);

      return ClienteMapper.mapToClienteDto(guardado);
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
      System.err.println("ERROR GENERAL=>" + e);
      throw new BusinessException("ERROR AL CREAR EL CLIENTE", e);
    }

  }

  @Override
  public ClienteDto getClienteById(Long cliId) {
    try {
      Cliente cliente = clienteRepository.findById(cliId)
          .orElseThrow(() -> new ResourceNotFoundException("NO SE HA ENCONTRADO UN CLIENTE CON ESE ID"));

      // Mapear el cliente (con direcciones) a clienteDto
      return ClienteMapper.mapToClienteDto(cliente);
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
      throw new BusinessException("ERROR AL OBTENER EL CLIENTE BY ID");
    }
  }

  @Override
  public List<ClienteDto> getAllCliente() {
    try {
      List<Cliente> clientes = clienteRepository.findAll();

      return clientes.stream().map((cliente) -> ClienteMapper.mapToClienteDto(cliente))
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
      throw new BusinessException("ERROR AL OBTENER TODOS LOS CLIENTES");
    }
  }

  @Override
  public ClienteDto updateCliente(Long cliId, ClienteDto clienteDto) {
    try {
      Cliente cliente = clienteRepository.findById(cliId)
          .orElseThrow(() -> new ResourceNotFoundException("NO SE HA ENCONTRADO UN CLIENTE CON ESE ID"));

      // FIXME: VALIDACIÓN LONGITUD
      if ("CEDULA".equals(cliente.getCliTipoIdentificacion()) &&
          cliente.getCliNumIdentificacion().length() != 10) {

        throw new ValidationException("LA CÉDULA DEBE TENER 10 CARACTERES");
      }

      if ("RUC".equals(cliente.getCliTipoIdentificacion()) &&
          cliente.getCliNumIdentificacion().length() != 13) {
        throw new ValidationException("EL RUC DEBE TENER 13 CARACTERES");
      }

      // FIXME: VALIDACION PARA EVITAR CLIENTES REPETIDOS
      List<Cliente> cliRepetido = clienteRepository
          .obtenerClienteByIdentificacion(clienteDto.getCliNumIdentificacion());

      if (cliRepetido != null && !cliRepetido.isEmpty() &&
          !cliente.getCliNumIdentificacion().equals(cliRepetido.get(0).getCliNumIdentificacion())) {
        throw new BusinessException("LA IDENTIFICACIÓN YA ESTÁ REGISTRADA");
      }

      // FIXME: VALIDACIONES PARA NUMERO DE TELEFONO
      if (cliente.getCliTelefono().length() != 10) {
        throw new ValidationException("EL NÚMERO DE TELÉFONO DEBE CONTENER 10 CARACTERES");
      }

      cliente.setCliTipoIdentificacion(clienteDto.getCliTipoIdentificacion());
      cliente.setCliNumIdentificacion(clienteDto.getCliNumIdentificacion());
      cliente.setCliNombre(clienteDto.getCliNombre());
      cliente.setCliCorreo(clienteDto.getCliCorreo());
      cliente.setCliTelefono(clienteDto.getCliTelefono());

      if (clienteDto.getDirecciones() != null) {
        cliente.getDirecciones().clear();

        List<Direccion> nuevasDirecciones = clienteDto.getDirecciones().stream()
            .map(direccionDto -> {
              Direccion direccion = DireccionMapper.mapToDireccion(direccionDto);
              direccion.setCliente(cliente);
              return direccion;
            })
            .collect(Collectors.toList());

        cliente.getDirecciones().addAll(nuevasDirecciones);
      }

      // Guardamos el cliente actualizado
      Cliente actualizar = clienteRepository.save(cliente);

      return ClienteMapper.mapToClienteDto(actualizar);
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
      System.err.println("ERROR =>" + e);
      throw new BusinessException("ERROR AL ACTUALIZAR EL CLIENTE");
    }
  }

  @Override
  public void deleteClienteById(Long cliId) {
    try {
      Cliente cliente = clienteRepository.findById(cliId)
          .orElseThrow(() -> new ResourceNotFoundException("NO SE HA ENCONTRADO UN CLIENTE CON ESE ID"));

      clienteRepository.delete(cliente);
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
      throw new BusinessException("ERROR AL ELIMINAR AL CLIENTE");
    }
  }

  @Override
  public List<ClienteDto> obtenerClienteNomIden(String buscar) {
    try {
      String nuevoTexto = "%" + buscar.toUpperCase() + "%";
      List<Cliente> clientes = clienteRepository.obtenerClienteNomIden(nuevoTexto);

      System.out.println("buscar ==>>" + buscar);
      System.out.println("nuevoTexto ==>>" + nuevoTexto);
      System.out.println("clientes ==>>" + clientes);

      return clientes.stream().map((cliente) -> ClienteMapper.mapToClienteDto(cliente))
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
      throw new BusinessException("ERROR AL OBTENER LOS CLIENTES POR NOMBRE O IDENTIFICACIÓN");
    }
  }

}
