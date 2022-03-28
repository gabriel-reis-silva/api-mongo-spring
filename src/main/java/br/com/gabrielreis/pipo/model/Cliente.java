package br.com.gabrielreis.pipo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Cliente {

    @JsonIgnore
    @Id
    String id;

    String nome;

    List<Beneficio> beneficio;

    public Cliente(String nome, List<Beneficio> beneficio) {
        this.nome = nome;
        this.beneficio = beneficio;
    }
}
