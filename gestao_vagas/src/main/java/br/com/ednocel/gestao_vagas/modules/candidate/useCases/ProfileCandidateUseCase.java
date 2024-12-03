package br.com.ednocel.gestao_vagas.modules.candidate.useCases;

import br.com.ednocel.gestao_vagas.exceptions.UserNotFoundException;
import br.com.ednocel.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.ednocel.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(Long idCandidate) {
        var candidate = this.candidateRepository.findById(idCandidate).orElseThrow(() -> {
            throw new UserNotFoundException();
        });

        return ProfileCandidateResponseDTO.builder()
                .description(candidate.getDescription())
                .name(candidate.getName())
                .email(candidate.getEmail())
                .username(candidate.getUsername())
                .id(candidate.getId())
                .build();
    }
}
