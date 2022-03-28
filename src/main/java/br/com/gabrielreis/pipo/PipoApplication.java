package br.com.gabrielreis.pipo;

import br.com.gabrielreis.pipo.model.Beneficio;
import br.com.gabrielreis.pipo.model.Cliente;
import br.com.gabrielreis.pipo.repository.BeneficioRepository;
import br.com.gabrielreis.pipo.repository.ClienteRepository;
import br.com.gabrielreis.pipo.repository.FuncionarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class PipoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PipoApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(BeneficioRepository beneficioRepository,
                             ClienteRepository clienteRepository,
                             FuncionarioRepository funcionarioRepository) {
        return args -> {
            Beneficio beneficio = new Beneficio("Norte Europa", List.of(1, 2, 3, 4));
            Beneficio beneficio1 = new Beneficio("Pampulha Intermédica", List.of(1, 2, 3, 5));
            Beneficio beneficio2 = new Beneficio("Dental Sorriso", List.of(1, 2, 6, 7));
            Beneficio beneficio3 = new Beneficio("Mente Sã, Corpo São", List.of(2, 8));

            Cliente cliente = new Cliente("Acme Co", List.of(beneficio, beneficio2));
            Cliente cliente1 = new Cliente("Tio Patinhas Bank", List.of(beneficio1, beneficio2, beneficio3));

            beneficioRepository.insert(List.of(beneficio, beneficio1, beneficio2, beneficio3));

            clienteRepository.insert(List.of(cliente, cliente1));

        };
    }

}
