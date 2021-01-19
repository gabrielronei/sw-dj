package br.com.gabriels.swdj.planetas;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlanetaRepository extends JpaRepository<Planeta, Long> {
    Boolean existsByNome(String nome);

    Optional<Planeta> findByNome(String nome);
}
