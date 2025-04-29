package com.alquimia.minegocio.service;

import java.util.List;

import com.alquimia.minegocio.dto.ClienteDto;

public interface ClienteService {
    ClienteDto createCliente(ClienteDto clienteDto);

    ClienteDto getClienteById(Long cliId);

    List<ClienteDto> getAllCliente();

    ClienteDto updateCliente(Long cliId, ClienteDto clienteDto);

    void deleteClienteById(Long cliId);

    List<ClienteDto> obtenerClienteNomIden(String buscar);
}
