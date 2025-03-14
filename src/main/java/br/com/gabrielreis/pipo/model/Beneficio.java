package br.com.gabrielreis.pipo.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Data
@Document
public class Beneficio {

    @Id
    String id;

    String nome;

    List<Integer> camposFuncionario;


    public Beneficio(String nome, List<Integer> camposFuncionario) {
        this.nome = nome;
        this.camposFuncionario = camposFuncionario;
    }
}
