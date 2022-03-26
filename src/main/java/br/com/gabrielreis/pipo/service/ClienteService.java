package br.com.gabrielreis.pipo.service;

import br.com.gabrielreis.pipo.model.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    List<Cliente> getClientes();

    Cliente postCliente(Cliente cliente);

    void deleteCliente(String id);

    boolean existsById(String id);

    Optional<Cliente> getClienteById(String id);
}
