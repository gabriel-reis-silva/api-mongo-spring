package br.com.gabrielreis.pipo.controller;

import br.com.gabrielreis.pipo.model.Beneficio;
import br.com.gabrielreis.pipo.service.BeneficioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static br.com.gabrielreis.pipo.constants.SecurityConstants.PRIVATE_URL;
import static br.com.gabrielreis.pipo.constants.SecurityConstants.TOKEN_PREFIX;

@RestController
@Api(value = "API de benefícios pipo saúde")
@CrossOrigin(origins = "*")
@RequestMapping(PRIVATE_URL + "/beneficio")
public class BeneficioController {

    @Autowired
    BeneficioService beneficioService;

    @ApiOperation(value = "Cria um Benefício", authorizations = {@Authorization(value = "Bearer token")})
    @PostMapping()
    public ResponseEntity<Beneficio> postBeneficio(@RequestBody Beneficio beneficio) {
        return ResponseEntity.status(201).body(beneficioService.postBeneficio(beneficio));
    }

    @ApiOperation(value = "Busca um benefício pelo seu id", authorizations = {@Authorization(value = "Bearer token")})
    @GetMapping("{id}")
    public ResponseEntity getById(@RequestParam String id) {
        Optional<Beneficio> beneficio = beneficioService.getBeneficioById(id);
        if(beneficio.isEmpty()){
            return ResponseEntity.status(404).body("Benefício não encontrado");
        }
        return ResponseEntity.status(200).body(beneficio);
    }

    @ApiOperation(value = "Retorna todos os benefícios", authorizations = {@Authorization(value = "Bearer token")})
    @GetMapping()
    public ResponseEntity getAll() {
        return ResponseEntity.status(200).body(beneficioService.getAll());
    }

    @ApiOperation(value = "Deleta Benefício por id", authorizations = {@Authorization(value = "Bearer token")})
    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@RequestParam String id) {
        if (beneficioService.getBeneficioById(id).isEmpty()) {
            return ResponseEntity.status(404).body("Benefício de id: " + id + " não encontrado");
        }
        beneficioService.deleteByid(id);
        return ResponseEntity.status(200).body("Benefício de id: " + id + " deletado.");
    }

    @ApiOperation(value = "Atualiza um benefício por id", authorizations = {@Authorization(value = "Bearer token")})
    @PutMapping("/{id}")
    public ResponseEntity updateById(@PathVariable String id, @RequestBody Beneficio beneficioAtributo) {
        Optional<Beneficio> beneficio = beneficioService.getBeneficioById(id);
        if (beneficio.isPresent()) {
            if (beneficioAtributo.getNome() != null) {
                beneficio.get().setNome(beneficioAtributo.getNome());
            }
            if (beneficioAtributo.getCamposFuncionario() != null) {
                beneficio.get().setCamposFuncionario(beneficioAtributo.getCamposFuncionario());
            }
            beneficioService.postBeneficio(beneficio.get());
            return ResponseEntity.status(200).build();
        }
            return ResponseEntity.status(404).body("Benefício não encontrado");
    }

}
