package tic.ordemFranciscana.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tic.ordemFranciscana.backend.model.Aprovacao;

@Repository
public interface AprovacaoRepository extends JpaRepository<Aprovacao, Long> {
}
