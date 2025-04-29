package com.alquimia.minegocio.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cli_id")
    private Long cliId;

    @Column(name = "cli_tipo_identificacion")
    private String cliTipoIdentificacion;

    @Column(name = "cli_num_identificacion", nullable = false, unique = true)
    private String cliNumIdentificacion;

    @Column(name = "cli_nombre")
    private String cliNombre;

    @Column(name = "cli_correo")
    private String cliCorreo;

    @Column(name = "cli_telefono")
    private String cliTelefono;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Direccion> direcciones = new ArrayList<>();
}
