package tic.ordemFranciscana.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tic.ordemFranciscana.backend.model.SaidaFinanceira;

@Repository
public interface SaidaFinanceiraRepository extends JpaRepository<SaidaFinanceira, Long> {
}
