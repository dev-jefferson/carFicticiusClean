package br.com.jhsgdev.carFicticiusClean.data.dto;

import br.com.jhsgdev.carFicticiusClean.data.model.Veiculo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class MarcaDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;

    @JsonIgnore
    List<Veiculo> veiculos;

    public MarcaDTO(Long id, String nome) {
        super();
        this.id = id;
        this.nome = nome;
    }

}