package br.com.ednocel.gestao_vagas.modules.company.repositories;

import br.com.ednocel.gestao_vagas.modules.company.entities.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {

    Optional<CompanyEntity> findByUsernameOrEmail(String username, String email);

    Optional<CompanyEntity> findByUsername(String username);
}
