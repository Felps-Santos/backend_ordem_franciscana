package tic.ordemFranciscana.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tic.ordemFranciscana.backend.model.Justificativa;

@Repository
public interface JustificativaRepository extends JpaRepository<Justificativa, Long> {
}
