package tic.ordemFranciscana.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tic.ordemFranciscana.backend.model.Grupo;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {
}
