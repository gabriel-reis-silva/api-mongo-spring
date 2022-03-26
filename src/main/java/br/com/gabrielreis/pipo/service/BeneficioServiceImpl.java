package br.com.gabrielreis.pipo.service;

import br.com.gabrielreis.pipo.model.Beneficio;
import br.com.gabrielreis.pipo.repository.BeneficioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeneficioServiceImpl implements BeneficioService{

    @Autowired
    BeneficioRepository beneficioRepository;

    @Override
    public Beneficio postBeneficio(Beneficio beneficio) {
        return beneficioRepository.save(beneficio);
    }

    @Override
    public List<Beneficio> getAll() {
        return beneficioRepository.findAll();
    }

    @Override
    public Optional<Beneficio> getBeneficioById(String id) {
        return beneficioRepository.findById(id);
    }

    @Override
    public void deleteByid(String id) {
        beneficioRepository.deleteById(id);
    }
}

