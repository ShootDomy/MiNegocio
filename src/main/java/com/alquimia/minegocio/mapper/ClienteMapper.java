package com.alquimia.minegocio.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.alquimia.minegocio.dto.ClienteDto;
import com.alquimia.minegocio.entity.Cliente;
import com.alquimia.minegocio.entity.Direccion;

public class ClienteMapper {
    public static ClienteDto mapToClienteDto(Cliente cliente) {
        return new ClienteDto(
                cliente.getCliId(),
                cliente.getCliTipoIdentificacion(),
                cliente.getCliNumIdentificacion(),
                cliente.getCliNombre(),
                cliente.getCliCorreo(),
                cliente.getCliTelefono(),
                cliente.getDirecciones() != null
                        ? cliente.getDirecciones().stream()
                                .map(DireccionMapper::mapToDireccionDto)
                                .collect(Collectors.toList())
                        : new ArrayList<>());
    }

    public static Cliente mapToCliente(ClienteDto clienteDto) {
        Cliente cliente = new Cliente();

        cliente.setCliId(clienteDto.getCliId());
        cliente.setCliTipoIdentificacion(clienteDto.getCliTipoIdentificacion());
        cliente.setCliNumIdentificacion(clienteDto.getCliNumIdentificacion());
        cliente.setCliCorreo(clienteDto.getCliCorreo());
        cliente.setCliNombre(clienteDto.getCliNombre());
        cliente.setCliTelefono(clienteDto.getCliTelefono());

        if (clienteDto.getDirecciones() != null) {
            List<Direccion> direcciones = clienteDto.getDirecciones().stream()
                    .map(direccionDto -> {
                        Direccion dir = DireccionMapper.mapToDireccion(direccionDto);
                        dir.setCliente(cliente);
                        return dir;
                    })
                    .collect(Collectors.toList());
            cliente.setDirecciones(direcciones);
        }

        return cliente;
    }
}
