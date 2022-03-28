package br.com.gabrielreis.pipo.repository;

import br.com.gabrielreis.pipo.model.Beneficio;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficioRepository extends MongoRepository<Beneficio, String> {
}
