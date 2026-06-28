package tic.ordemFranciscana.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tic.ordemFranciscana.backend.model.Membro;

@Repository
public interface MembroRepository extends JpaRepository<Membro, Long> {
}
