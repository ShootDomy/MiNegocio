package com.alquimia.minegocio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DireccionDto {
    private Long dirId;
    private String dirProvincia;
    private String dirCiudad;
    private String dirdireccion;
    private Boolean dirMatriz;
    private Long cliId;
}
