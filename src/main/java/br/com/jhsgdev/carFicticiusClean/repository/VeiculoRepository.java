package br.com.jhsgdev.carFicticiusClean.repository;

import br.com.jhsgdev.carFicticiusClean.data.model.Marca;
import br.com.jhsgdev.carFicticiusClean.data.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@EnableJpaRepositories
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

//    @Transactional(readOnly = true)
//    Optional<Veiculo> findByMarca(Marca entity);

}