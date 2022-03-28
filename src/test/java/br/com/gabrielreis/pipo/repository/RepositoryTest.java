package br.com.gabrielreis.pipo.repository;

import br.com.gabrielreis.pipo.model.Beneficio;
import br.com.gabrielreis.pipo.model.Cliente;
import br.com.gabrielreis.pipo.model.Funcionario;
import br.com.gabrielreis.pipo.repository.BeneficioRepository;
import br.com.gabrielreis.pipo.repository.ClienteRepository;
import br.com.gabrielreis.pipo.repository.FuncionarioRepository;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class RepositoryTest {

    private static final String IP = "localhost";
    private static final int PORT = 28017; // <- set MongoDB port

    @TestConfiguration
    @ComponentScan("br.com.gabrielreis.pipo.repository")
    static class Config {
        @Bean
        public MongodConfig embeddedMongoConfiguration() throws IOException {
            return MongodConfig.builder()
                    .version(Version.V4_0_2) // <- set MongoDB version
                    .net(new Net(IP, PORT, Network.localhostIsIPv6()))
                    .build();
        }
    }

    @Autowired private BeneficioRepository beneficioRepository;
    @Autowired private ClienteRepository clienteRepository;
    @Autowired private FuncionarioRepository funcionarioRepository;
    @Autowired private MongoTemplate mongo;

    @BeforeEach
    void setUp() {
         mongo.getDb().drop();
         postTestObjects();
    }

    public void postTestObjects(){
        Beneficio beneficio = new Beneficio("Plano Dental", List.of(1,2,3));
        beneficio.setId("mock-1");
        beneficioRepository.save(beneficio);
        Cliente cliente = new Cliente("Josivaldo", List.of(beneficio));
        cliente.setId("mock-1-c");
        clienteRepository.save(cliente);
        Funcionario funcionario = new Funcionario("Carlinhos", "117.545.130-42",new Date(),"carlos@email.com","Rua 2", 78.0, 1.70, 7.0, List.of(beneficio));
        funcionario.setId("mock-1-f");
        funcionarioRepository.save(funcionario);
    }

    @Test
    void createBeneficio() {
        String resposta = beneficioRepository.findById("mock-1").get().getNome();
        assertEquals("Plano Dental",(resposta));
    }

    @Test
    void createCliente() {
        String resposta = clienteRepository.findById("mock-1-c").get().getNome();
        assertEquals("Josivaldo",(resposta));
    }

    @Test
    void createFuncionario() {
        String resposta = funcionarioRepository.findById("mock-1-f").get().getNome();
        assertEquals("Carlinhos",(resposta));
    }
}


