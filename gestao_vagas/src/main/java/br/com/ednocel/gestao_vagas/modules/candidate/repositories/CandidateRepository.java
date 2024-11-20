package br.com.ednocel.gestao_vagas.modules.candidate.repositories;

import br.com.ednocel.gestao_vagas.modules.candidate.entities.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CandidateRepository extends JpaRepository<CandidateEntity, Long> {

    Optional<CandidateEntity> findByUsernameOrEmail(String username, String email);
}
