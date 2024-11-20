package br.com.ednocel.gestao_vagas.modules.company.repositories;

import br.com.ednocel.gestao_vagas.modules.company.entities.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<JobEntity, Long> {
}
