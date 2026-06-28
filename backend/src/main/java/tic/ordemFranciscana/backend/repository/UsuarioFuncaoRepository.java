package tic.ordemFranciscana.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tic.ordemFranciscana.backend.model.UsuarioFuncao;

@Repository
public interface UsuarioFuncaoRepository extends JpaRepository<UsuarioFuncao, Long> {
}
