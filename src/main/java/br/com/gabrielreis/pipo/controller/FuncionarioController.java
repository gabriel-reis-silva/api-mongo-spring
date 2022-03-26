package br.com.gabrielreis.pipo.controller;

import br.com.gabrielreis.pipo.model.Beneficio;
import br.com.gabrielreis.pipo.model.Funcionario;
import br.com.gabrielreis.pipo.service.BeneficioService;
import br.com.gabrielreis.pipo.service.FuncionarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static br.com.gabrielreis.pipo.constants.SecurityConstants.PRIVATE_URL;

@RestController
@Api(value = "API de benefícios pipo saúde")
@CrossOrigin(origins = "*")
@RequestMapping(PRIVATE_URL + "/funcionario")
public class FuncionarioController {

    @Autowired
    FuncionarioService funcionarioService;

    @Autowired
    BeneficioService beneficioservice;

    @ApiOperation(value = "Cria um Funcionário", authorizations = {@Authorization(value = "Bearer token")})
    @PostMapping()
    public ResponseEntity postFuncionario(@RequestBody Funcionario funcionario) {
        try {
            funcionarioService.postFuncionario(funcionario);
            return ResponseEntity.status(201).body(funcionario);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body("ERRO: " + e);
        }
    }

    @ApiOperation(value = "Retorna uma lista de Funcionários", authorizations = {@Authorization(value = "Bearer token")})
    @GetMapping()
    public ResponseEntity getAll() {
        return ResponseEntity.status(200).body(funcionarioService.getall());
    }

    @ApiOperation(value = "Deleta um Funcionário pelo id", authorizations = {@Authorization(value = "Bearer token")})
    @DeleteMapping("/{id}")
    public ResponseEntity deletefuncionario(@PathVariable String id) {
        if (funcionarioService.existsById(id)) {
            funcionarioService.deleteFuncionario(id);
            return ResponseEntity.status(200).body("Funcionario de id: " + id + " deletado");
        } else {
            return ResponseEntity.status(200).body("Funcionario de id: " + id + " não encontrado");
        }
    }

    @ApiOperation(value = "Atualiza um Funcionário pelo id", authorizations = {@Authorization(value = "Bearer token")})
    @PutMapping("/{id}")
    public ResponseEntity updateById(@PathVariable String id, @RequestBody Funcionario funcionarioAtributo) {
        Optional<Funcionario> funcionario = funcionarioService.findById(id);
        if (funcionario.isPresent()) {
            if (funcionarioAtributo.getNome() != null) {
                funcionario.get().setNome(funcionarioAtributo.getNome());
            }
            if (funcionarioAtributo.getCpf() != null) {
                funcionario.get().setCpf(funcionarioAtributo.getCpf());
            }
            if (funcionarioAtributo.getDataAdmissao() != null) {
                funcionario.get().setDataAdmissao(funcionarioAtributo.getDataAdmissao());
            }
            if (funcionarioAtributo.getEmail() != null) {
                funcionario.get().setEmail(funcionarioAtributo.getEmail());
            }
            if (funcionarioAtributo.getEndereco() != null) {
                funcionario.get().setEndereco(funcionarioAtributo.getEndereco());
            }
            if (funcionarioAtributo.getPeso() != null) {
                funcionario.get().setPeso(funcionarioAtributo.getPeso());
            }
            if (funcionarioAtributo.getAltura() != null) {
                funcionario.get().setAltura(funcionarioAtributo.getAltura());
            }
            if (funcionarioAtributo.getHorasMeditadas() != null) {
                funcionario.get().setHorasMeditadas(funcionarioAtributo.getHorasMeditadas());
            }
            if (funcionarioAtributo.getBeneficios() != null) {
                Beneficio beneficio = this.beneficioservice.getBeneficioById(funcionario.get().getBeneficios().getId())
                        .orElseThrow(() ->
                                new IllegalArgumentException
                                        ("Benefício de id:" + funcionario.get().getBeneficios().getId() + " Não encontrado"));
                funcionario.get().setBeneficios(beneficio);
            }
            funcionarioService.postFuncionario(funcionario.get());
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).body("Funcionario não encontrado");
    }

    @ApiOperation(value = "Retorna um Funcionário pelo id", authorizations = {@Authorization(value = "Bearer token")})
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable String id) {
        Optional<Funcionario> funcionario = funcionarioService.findById(id);
        if (funcionario.isPresent()) {
            return ResponseEntity.status(200).body(funcionario);
        } else {
            return ResponseEntity.status(404).body("Funcionario não encontrado");
        }
    }

}
