package tic.ordemFranciscana.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tic.ordemFranciscana.backend.model.Presenca;

@Repository
public interface PresencaRepository extends JpaRepository<Presenca, Long> {
}
