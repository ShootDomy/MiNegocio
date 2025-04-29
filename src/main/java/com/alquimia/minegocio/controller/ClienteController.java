package com.alquimia.minegocio.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alquimia.minegocio.dto.ClienteDto;
import com.alquimia.minegocio.service.ClienteService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/cliente")
public class ClienteController {
    private ClienteService clienteService;

    /**
     * Controlador para insertar clientes
     * 
     * @body clienteDto
     */
    @PostMapping
    public ResponseEntity<ClienteDto> createCliente(@RequestBody ClienteDto clienteDto) {
        ClienteDto guardar = clienteService.createCliente(clienteDto);
        return new ResponseEntity<>(guardar, HttpStatus.CREATED);
    }

    /**
     * Controlador para obtener clientes por ID
     * 
     * @param cliId
     */
    @GetMapping("{cliId}")
    public ResponseEntity<ClienteDto> getClienteById(@PathVariable("cliId") Long cliId) {
        ClienteDto obtener = clienteService.getClienteById(cliId);
        return ResponseEntity.ok(obtener);
    }

    /**
     * Controlador para obtener todos los clientes
     * 
     */
    @GetMapping("/all")
    public ResponseEntity<List<ClienteDto>> getAllClientes() {
        List<ClienteDto> clientes = clienteService.getAllCliente();
        return ResponseEntity.ok(clientes);
    }

    /**
     * Controlador para actualizar un cliente
     * 
     * @param cliId
     * @body clienteDto
     */
    @PatchMapping("{cliId}")
    public ResponseEntity<ClienteDto> updateCliente(@PathVariable("cliId") Long cliId,
            @RequestBody ClienteDto clienteDto) {
        ClienteDto actualizar = clienteService.updateCliente(cliId, clienteDto);
        return ResponseEntity.ok(actualizar);
    }

    /**
     * Controlador para eliminar clientes
     * 
     * @param cliId
     */
    @DeleteMapping("{cliId}")
    public ResponseEntity<String> deleteClienteById(@PathVariable("cliId") Long cliId) {
        clienteService.deleteClienteById(cliId);
        return ResponseEntity.ok("EL CLIENTE FUE ELIMINADO EXITOSAMENTE");
    }

    /**
     * Controlador para obtener todos los clientes FILTRADO POR NOMBRE O
     * IDENTIFICACION
     * 
     * @param buscar
     */
    @GetMapping("/buscar/{buscar}")
    public ResponseEntity<List<ClienteDto>> obtenerClienteNomIden(@PathVariable("buscar") String buscar) {
        List<ClienteDto> clientes = clienteService.obtenerClienteNomIden(buscar);
        return ResponseEntity.ok(clientes);
    }
}
