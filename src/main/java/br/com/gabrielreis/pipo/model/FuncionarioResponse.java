package br.com.gabrielreis.pipo.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.mongodb.core.mapping.Document;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Data
@Document
public class FuncionarioResponse {

    String nome;
    @CPF
    String cpf;

}
