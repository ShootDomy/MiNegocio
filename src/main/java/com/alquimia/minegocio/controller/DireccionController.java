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

import com.alquimia.minegocio.dto.DireccionDto;
import com.alquimia.minegocio.service.DireccionService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/direccion")
public class DireccionController {
    private DireccionService direccionService;

    /**
     * Controlador para insertar direcciones
     * 
     * @body direccionDto
     */
    @PostMapping
    public ResponseEntity<DireccionDto> createDireccion(@RequestBody DireccionDto direccionDto) {
        DireccionDto guardar = direccionService.createDireccion(direccionDto);
        return new ResponseEntity<>(guardar, HttpStatus.CREATED);
    }

    /**
     * Controlador para obtener direcciones por ID
     * 
     * @param dirId
     */
    @GetMapping("{dirId}")
    public ResponseEntity<DireccionDto> getDireccionById(@PathVariable("dirId") Long dirId) {
        DireccionDto obtener = direccionService.getDireccionById(dirId);
        return ResponseEntity.ok(obtener);
    }

    /**
     * Controlador para obtener todos los direcciones
     * 
     */
    @GetMapping("/all")
    public ResponseEntity<List<DireccionDto>> getAllDireccions() {
        List<DireccionDto> direcciones = direccionService.getAllDireccion();
        return ResponseEntity.ok(direcciones);
    }

    /**
     * Controlador para actualizar una direccion
     * 
     * @param dirId
     * @body direccionDto
     */
    @PatchMapping("{dirId}")
    public ResponseEntity<DireccionDto> updateDireccion(@PathVariable("dirId") Long dirId,
            @RequestBody DireccionDto direccionDto) {
        DireccionDto actualizar = direccionService.updateDireccion(dirId, direccionDto);
        return ResponseEntity.ok(actualizar);
    }

    /**
     * Controlador para eliminar direcciones
     * 
     * @param cliI
     */
    @DeleteMapping("{dirId}")
    public ResponseEntity<String> deleteDireccionById(@PathVariable("dirId") Long dirId) {
        direccionService.deleteDireccionById(dirId);
        return ResponseEntity.ok("LA DIRECCIÓN FUE ELIMINADA EXITOSAMENTE");
    }

}
