package tic.ordemFranciscana.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tic.ordemFranciscana.backend.model.EtapaFormacao;

@Repository
public interface EtapaFormacaoRepository extends JpaRepository<EtapaFormacao, Long> {
}
