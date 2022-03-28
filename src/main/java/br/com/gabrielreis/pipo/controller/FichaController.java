package br.com.gabrielreis.pipo.controller;

import br.com.gabrielreis.pipo.model.Beneficio;
import br.com.gabrielreis.pipo.model.Funcionario;
import br.com.gabrielreis.pipo.service.FuncionarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

@RestController
@Api(value = "API de benefícios pipo saúde")
@CrossOrigin(origins = "*")
@RequestMapping("/ficha")
public class FichaController {

    @Autowired
    FuncionarioService funcionarioService;

    @Autowired
    MongoTemplate mongoTemplate;

    @ApiOperation(value = "Retorna a ficha de um Funcionario pelo seu id", authorizations = {@Authorization(value = "Bearer token")})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getBeneficioByFuncionario(@RequestParam String id) {
        System.out.println("id" + id);
        Optional<Funcionario> funcionario = funcionarioService.findById(id);
        StringJoiner resposta = new StringJoiner(", ");
        if (funcionario.isPresent()) {
            System.out.println(funcionario.get());
            List<Beneficio> beneficio = funcionario.get().getBeneficios();
            for (int iB = 0; iB < beneficio.size(); iB++) {
                if (funcionarioService.existsById(id)) {
                    List<Object> lista = new ArrayList<>();
                    for (int i = 0; i < beneficio.get(iB).getCamposFuncionario().size(); i++) {
                        lista.add(funcionario.get().selectField(beneficio.get(iB).getCamposFuncionario().get(i)));
                    }
                    System.out.println("lista" + lista);
                    resposta.add("{\"nomeBeneficio\":\"" + beneficio.get(iB).getNome() + "\"}," + "{\"dadosFuncionario\":" + lista + "}");
                    System.out.println("RESPOSTA " + resposta);
                }
            }
            return ResponseEntity.status(200).body("[" + resposta + "]");
        }
        return ResponseEntity.status(404).body("Benefício ou Funcionário não encontrado");
    }
}
