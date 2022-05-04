package br.com.jhsgdev.carFicticiusClean.data.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
public class RetornoGastoCombustivelDTO extends VeiculoDTO {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Setter
    private int ano;
    @Getter@Setter
    private Double qtdCombustivelGasto;
    @Getter@Setter
    private Double valorTotalGastoCombustivel;


    public RetornoGastoCombustivelDTO(Long id, String nome, MarcaDTO marca, String modelo, Date dataFabricacao,
                                      Integer consumoMedioCidade, Integer consumoMedioRodovia) {
        super(id, nome, marca, modelo, dataFabricacao, consumoMedioCidade, consumoMedioRodovia);
    }


    @Override
    @JsonIgnore
    public Date getDataFabricacao(){
        return super.getDataFabricacao();
    };

    @Override
    @JsonIgnore
    public Integer getConsumoMedioCidade(){
        return super.getConsumoMedioCidade();
    };

    @Override
    @JsonIgnore
    public Integer getConsumoMedioRodovia(){
        return super.getConsumoMedioRodovia();
    };


    public int getAno() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(super.getDataFabricacao());
        ano = calendar.get(Calendar.YEAR);
        return ano;
    }


    public void calculaGastoCombustivel(Double precoGasolina, Integer kmTotalPercorridoCidade, Integer kmTotalPercorridoRodovia) {

        Double qtdLitrosCombustivelCidade = Double.valueOf(Math.ceil(kmTotalPercorridoCidade)/super.getConsumoMedioCidade());
        Double qtdLitrosCombustivelRodovia = Double.valueOf(Math.ceil(kmTotalPercorridoRodovia)/super.getConsumoMedioRodovia());
        Double totalLitros = qtdLitrosCombustivelCidade + qtdLitrosCombustivelRodovia;
        this.setQtdCombustivelGasto(totalLitros);
        this.setValorTotalGastoCombustivel(totalLitros * precoGasolina);
    }

}
