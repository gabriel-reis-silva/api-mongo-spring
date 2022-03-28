package br.com.gabrielreis.pipo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Data
@Document
public class Funcionario {

    @Id
    String id;
    String nome;
    @CPF
    String cpf;
    Date dataAdmissao;
    @Email
    String email;
    String endereco;
    Double peso;
    Double altura;
    Double horasMeditadas;
    List<Beneficio> beneficios;

    public Object selectField(Integer position) {
        switch (position) {
            case 1:
                return "{\"nome\":\""+getNome()+"\"}";
            case 2:
               return "{\"cpf\":\""+getCpf()+"\"}";
            case 3:
                return "{\"dataAdmissao\":\""+getDataAdmissao()+"\"}";
            case 4:
                return "{\"email\":\""+getEmail()+"\"}";
            case 5:
                return "{\"endereco\":\""+getEndereco()+"\"}";
            case 6:
                return "{\"peso\":"+getPeso()+"}";
            case 7:
                return "{\"altura\":"+getAltura()+"}";
            case 8:
                return "{\"horasMeditadas\":"+getHorasMeditadas()+"}";
            default:
                return "Sem benefícios";
        }
    }

    public Funcionario(String nome, String cpf, Date dataAdmissao, String email, String endereco, Double peso, Double altura, Double horasMeditadas, List<Beneficio> beneficios) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataAdmissao = dataAdmissao;
        this.email = email;
        this.endereco = endereco;
        this.peso = peso;
        this.altura = altura;
        this.horasMeditadas = horasMeditadas;
        this.beneficios = beneficios;
    }
}