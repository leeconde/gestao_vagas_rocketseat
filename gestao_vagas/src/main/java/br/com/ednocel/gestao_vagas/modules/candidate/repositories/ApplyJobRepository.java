package br.com.ednocel.gestao_vagas.modules.candidate.repositories;

import br.com.ednocel.gestao_vagas.modules.candidate.entities.ApplyJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplyJobRepository extends JpaRepository<ApplyJobEntity, Long> {
}
