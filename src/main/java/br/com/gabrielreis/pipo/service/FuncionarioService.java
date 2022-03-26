package br.com.gabrielreis.pipo.service;

import br.com.gabrielreis.pipo.model.Funcionario;

import java.util.List;
import java.util.Optional;

public interface FuncionarioService {

    Funcionario postFuncionario(Funcionario funcionario);
    
    List<Funcionario> getall();

    Optional<Funcionario> findById(String id);

    void deleteFuncionario(String id);

    boolean existsById(String id);

}
