package tic.ordemFranciscana.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tic.ordemFranciscana.backend.model.HistoricoFormacao;

@Repository
public interface HistoricoFormacaoRepository extends JpaRepository<HistoricoFormacao, Long> {
}
