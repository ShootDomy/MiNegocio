package com.alquimia.minegocio.RepositoryTest;

// import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.alquimia.minegocio.entity.Cliente;
import com.alquimia.minegocio.repository.ClienteRepository;

@DataJpaTest
public class ClienteRepositoryTest {
  @Autowired
  private ClienteRepository clienteRepository;

  @Test
  void createCliente() {
    Cliente cliente = Cliente.builder()
        .cliTipoIdentificacion("CEDULA")
        .cliNumIdentificacion("0258741369")
        .cliNombre("PEDRO")
        .cliCorreo("pedro@correo.com")
        .cliTelefono("0963258741")
        .build();

    Cliente cliente1 = clienteRepository.save(cliente);

    assertThat(cliente1).isEqualTo(cliente);
    // assertEquals(cliente, cliente1);
  }
}
