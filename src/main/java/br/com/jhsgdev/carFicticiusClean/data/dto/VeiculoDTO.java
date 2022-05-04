package br.com.jhsgdev.carFicticiusClean.data.dto;


import br.com.jhsgdev.carFicticiusClean.data.model.Marca;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = { "marca" })
public class VeiculoDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private MarcaDTO marca;
    private String modelo;
    private Date dataFabricacao;
    private Integer consumoMedioCidade;
    private Integer consumoMedioRodovia;


}
