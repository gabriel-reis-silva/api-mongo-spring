package br.com.gabrielreis.pipo.controller;

import br.com.gabrielreis.pipo.model.Beneficio;
import br.com.gabrielreis.pipo.model.Cliente;
import br.com.gabrielreis.pipo.service.BeneficioService;
import br.com.gabrielreis.pipo.service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.com.gabrielreis.pipo.constants.SecurityConstants.PRIVATE_URL;


@RestController
@Api(value = "API de benefícios pipo saúde")
@CrossOrigin(origins = "*")
@RequestMapping(PRIVATE_URL + "/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @Autowired
    BeneficioService beneficioService;

    @ApiOperation(value = "Cria um Cliente", authorizations = {@Authorization(value = "Bearer token")})
    @PostMapping()
    public ResponseEntity postCliente(@RequestBody Cliente cliente) {
        try {
            clienteService.postCliente(cliente);
            return ResponseEntity.status(201).body(cliente);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body("ERRO: " + e);
        }
    }

    @ApiOperation(value = "Retorna todos os Clientes", authorizations = {@Authorization(value = "Bearer token")})
    @GetMapping()
    public ResponseEntity getAll() {
        return ResponseEntity.status(200).body(clienteService.getClientes());
    }

    @ApiOperation(value = "Deleta um Cliente pelo seu id", authorizations = {@Authorization(value = "Bearer token")})
    @DeleteMapping("/{id}")
    public ResponseEntity deleteCliente(@PathVariable String id) {
        if (clienteService.existsById(id)) {
            clienteService.deleteCliente(id);
            return ResponseEntity.status(200).body("Cliente de id: " + id + " deletado");
        } else {
            return ResponseEntity.status(200).body("Cliente de id: " + id + " não encontrado");
        }
    }

    @ApiOperation(value = "Atualiza um Cliente pelo id", authorizations = {@Authorization(value = "Bearer token")})
    @PutMapping("/{id}")
    public ResponseEntity updateById(@PathVariable String id, @RequestBody Cliente clienteAtributo, String codBeneficio) {
        Optional<Cliente> cliente = clienteService.getClienteById(id);
        if (cliente.isPresent()) {
            if (clienteAtributo.getNome() != null) {
                cliente.get().setNome(clienteAtributo.getNome());
            }
            if (clienteAtributo.getBeneficio() != null) {
                Optional<Beneficio> beneficio = beneficioService.getBeneficioById(codBeneficio);
                if (beneficio.isPresent()) {
                    List<Beneficio> lista = new ArrayList<>();
                    lista.add(beneficio.get());
                    cliente.get().setBeneficio(lista);
                } else {
                    return ResponseEntity.status(404).body("Beneficio não encontrado");
                }
            }
            clienteService.postCliente(cliente.get());
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).body("Cliente não encontrado");
    }

    @ApiOperation(value = "Retorna um Cliente pelo id", authorizations = {@Authorization(value = "Bearer token")})
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable String id) {
        Optional<Cliente> cliente = clienteService.getClienteById(id);
        if (cliente.isPresent()) {
            return ResponseEntity.status(200).body(cliente);
        } else {
            return ResponseEntity.status(404).body("Cliente não encontrado");
        }
    }
}
