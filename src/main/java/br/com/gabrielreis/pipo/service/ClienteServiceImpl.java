package br.com.gabrielreis.pipo.service;

import br.com.gabrielreis.pipo.model.Beneficio;
import br.com.gabrielreis.pipo.model.Cliente;
import br.com.gabrielreis.pipo.repository.BeneficioRepository;
import br.com.gabrielreis.pipo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    BeneficioRepository beneficioRepository;

    @Override
    public List<Cliente> getClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente postCliente(Cliente cliente) {
        Beneficio beneficio = this.beneficioRepository.findById(cliente.getBeneficio().getId())
                .orElseThrow(() ->
                        new IllegalArgumentException
                                ("Benefício de id:" + cliente.getBeneficio().getId() + " Não encontrado"));
        cliente.setBeneficio(beneficio);
        return clienteRepository.save(cliente);
    }

    @Override
    public void deleteCliente(String id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return clienteRepository.existsById(id);
    }

    @Override
    public Optional<Cliente> getClienteById(String id) {
        return clienteRepository.findById(id);
    }
}
