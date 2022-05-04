package br.com.jhsgdev.carFicticiusClean.data.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name="veiculos")
public class Veiculo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nome;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name="marca_id", nullable=false)
    private Marca marca;

    @Column(nullable = false, length = 50)
    private String modelo;

    @Column(name = "data_fabricacao", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataFabricacao;

    @Column(name = "consumo_medio_cidade", nullable = false)
    private Integer consumoMedioCidade;

    @Column(name = "consumo_medio_rodovia", nullable = false)
    private Integer consumoMedioRodovia;

}
