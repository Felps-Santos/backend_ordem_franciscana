package tic.ordemFranciscana.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tic.ordemFranciscana.backend.model.ParticipacaoGrupo;

@Repository
public interface ParticipacaoGrupoRepository extends JpaRepository<ParticipacaoGrupo, Long> {
}
