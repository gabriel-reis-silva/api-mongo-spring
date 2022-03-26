package br.com.gabrielreis.pipo.service;

import br.com.gabrielreis.pipo.model.Beneficio;

import java.util.List;
import java.util.Optional;

public interface BeneficioService {

    Beneficio postBeneficio(Beneficio beneficio);

    Optional<Beneficio> getBeneficioById(String id);

    List<Beneficio> getAll();

    void deleteByid(String id);
}
