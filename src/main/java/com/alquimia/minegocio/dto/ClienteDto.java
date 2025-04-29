package com.alquimia.minegocio.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto {
    private Long cliId;
    private String cliTipoIdentificacion;
    private String cliNumIdentificacion;
    private String cliNombre;
    private String cliCorreo;
    private String cliTelefono;

    private List<DireccionDto> direcciones;
}
