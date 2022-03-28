package br.com.gabrielreis.pipo.service;

import br.com.gabrielreis.pipo.model.Beneficio;
import br.com.gabrielreis.pipo.model.Funcionario;
import br.com.gabrielreis.pipo.repository.BeneficioRepository;
import br.com.gabrielreis.pipo.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    BeneficioRepository beneficioRepository;

    @Override
    public Funcionario postFuncionario(Funcionario funcionario) {
        List<Beneficio> lista = new ArrayList<>();
        for (int i = 0; i < funcionario.getBeneficios().size(); i++) {
            lista.add(beneficioRepository.findById(funcionario.getBeneficios().get(i).getId()).get());
        }
        funcionario.setBeneficios(lista);
        return funcionarioRepository.save(funcionario);
    }

    @Override
    public List<Funcionario> getall() {
        return funcionarioRepository.findAll();
    }

    @Override
    public Optional<Funcionario> findById(String id) {
        return funcionarioRepository.findById(id);
    }

    @Override
    public void deleteFuncionario(String id) {
        funcionarioRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return funcionarioRepository.existsById(id);
    }
}
