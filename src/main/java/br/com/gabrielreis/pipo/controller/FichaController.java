package br.com.gabrielreis.pipo.controller;

import br.com.gabrielreis.pipo.model.Beneficio;
import br.com.gabrielreis.pipo.model.Funcionario;
import br.com.gabrielreis.pipo.model.FuncionarioResponse;
import br.com.gabrielreis.pipo.repository.FuncionarioRepository;
import br.com.gabrielreis.pipo.service.FuncionarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static br.com.gabrielreis.pipo.constants.SecurityConstants.PRIVATE_URL;

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
    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getBeneficioByFuncionario(@RequestParam String id) {
        System.out.println("id"+id);
        Optional<Funcionario> funcionario = funcionarioService.findById(id);
        if(funcionario.isPresent()) {
            System.out.println(funcionario.get());
            Beneficio beneficio = funcionario.get().getBeneficios();
            if (funcionarioService.existsById(id) || beneficio != null) {
                List<Object> lista = new ArrayList<>();
                for (int i = 0; i < beneficio.getCamposFuncionario().size(); i++) {
                    lista.add(funcionario.get().selectField(beneficio.getCamposFuncionario().get(i)));
                }
                System.out.println("lista" + lista);
                return ResponseEntity.status(200).body("[{\"nomeBeneficio\":\""+beneficio.getNome()+"\"},"+"{\"dadosFuncionario\":"+lista+"}]");
            }
        }
        return ResponseEntity.status(404).body("Benefício ou Funcionário não encontrado");
    }
}
