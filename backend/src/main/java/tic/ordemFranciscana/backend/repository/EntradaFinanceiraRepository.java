package tic.ordemFranciscana.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tic.ordemFranciscana.backend.model.EntradaFinanceira;

@Repository
public interface EntradaFinanceiraRepository extends JpaRepository<EntradaFinanceira, Long> {
}
