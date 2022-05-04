package br.com.jhsgdev.carFicticiusClean.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = { "veiculos" })
@Table(name="marcas")
public class Marca implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80)
    private String nome;

    @OneToMany(mappedBy = "marca", fetch = FetchType.LAZY)
    List<Veiculo> veiculos;

    public Marca(Long id, String nome) {
        super();
        this.id = id;
        this.nome = nome;
    }

}
