package br.com.gabrielreis.pipo.repository;

import br.com.gabrielreis.pipo.model.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClienteRepository extends MongoRepository<Cliente, String> {
}
