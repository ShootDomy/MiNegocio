package com.alquimia.minegocio.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "direccion")
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dir_id")
    private Long dirId;

    @Column(name = "dir_provincia")
    private String dirProvincia;

    @Column(name = "dir_ciudad")
    private String dirCiudad;

    @Column(name = "dir_direccion")
    private String dirdireccion;

    @Column(name = "dir_matriz")
    private Boolean dirMatriz;

    // @Column(name = "cli_id")
    // private Long cliId;

    @ManyToOne
    @JoinColumn(name = "cli_id")
    private Cliente cliente;
}
