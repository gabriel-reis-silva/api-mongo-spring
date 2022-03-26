package br.com.gabrielreis.pipo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Data
@Document
public class Cliente {

    @JsonIgnore
    String id;

    String nome;

    Beneficio beneficio;

}
