package tic.ordemFranciscana.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tic.ordemFranciscana.backend.model.Funcao;

@Repository
public interface FuncaoRepository extends JpaRepository<Funcao, Long> {
}
