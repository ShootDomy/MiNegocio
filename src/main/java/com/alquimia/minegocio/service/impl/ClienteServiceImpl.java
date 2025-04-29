package com.alquimia.minegocio.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.alquimia.minegocio.dto.ClienteDto;
import com.alquimia.minegocio.entity.Cliente;
import com.alquimia.minegocio.entity.Direccion;
import com.alquimia.minegocio.exception.ResourceNotFoundException;
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

            for (Direccion direccion : cliente.getDirecciones()) {
                direccion.setCliente(cliente);
            }

            Cliente guardado = clienteRepository.save(cliente);

            return ClienteMapper.mapToClienteDto(guardado);
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'createCliente'");
        }
    }

    @Override
    public ClienteDto getClienteById(Long cliId) {
        try {
            Cliente cliente = clienteRepository.findById(cliId)
                    .orElseThrow(() -> new ResourceNotFoundException("NO SE HA ENCONTRADO UN CLIENTE CON ESE ID"));

            // Mapear el cliente (con direcciones) a clienteDto
            return ClienteMapper.mapToClienteDto(cliente);
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'getClienteById'");
        }
    }

    @Override
    public List<ClienteDto> getAllCliente() {
        try {
            List<Cliente> clientes = clienteRepository.findAll();

            return clientes.stream().map((cliente) -> ClienteMapper.mapToClienteDto(cliente))
                    .collect((Collectors.toList()));
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'getAllCliente'");
        }
    }

    @Override
    public ClienteDto updateCliente(Long cliId, ClienteDto clienteDto) {
        try {
            Cliente cliente = clienteRepository.findById(cliId)
                    .orElseThrow(() -> new ResourceNotFoundException("NO SE HA ENCONTRADO UN CLIENTE CON ESE ID"));

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
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'updateCliente'");
        }
    }

    @Override
    public void deleteClienteById(Long cliId) {
        try {
            Cliente cliente = clienteRepository.findById(cliId)
                    .orElseThrow(() -> new ResourceNotFoundException("NO SE HA ENCONTRADO UN CLIENTE CON ESE ID"));

            clienteRepository.delete(cliente);
        } catch (Exception e) {
            throw new UnsupportedOperationException("Error eliminando cliente: " + e.getMessage());
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

        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'obtenerClienteNomIden'");
        }
    }

}
