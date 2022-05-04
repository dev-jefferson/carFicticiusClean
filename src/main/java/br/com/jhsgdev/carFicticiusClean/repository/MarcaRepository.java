package br.com.jhsgdev.carFicticiusClean.repository;

import br.com.jhsgdev.carFicticiusClean.data.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@EnableJpaRepositories
public interface MarcaRepository extends JpaRepository<Marca, Long> {

}