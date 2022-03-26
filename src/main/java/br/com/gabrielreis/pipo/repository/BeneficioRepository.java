package br.com.gabrielreis.pipo.repository;

import br.com.gabrielreis.pipo.model.Beneficio;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BeneficioRepository extends MongoRepository<Beneficio, String> {
}
