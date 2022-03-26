package br.com.gabrielreis.pipo.repository;

import br.com.gabrielreis.pipo.model.Funcionario;
import org.springframework.data.mongodb.core.ExecutableFindOperation;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface FuncionarioRepository extends MongoRepository<Funcionario, String> {

    @Query("{'cpf': ?0}, {'name':1, '_id':0}")
    Funcionario findByCpf (String cpf);

    Funcionario findFuncionarioById(String id);
}