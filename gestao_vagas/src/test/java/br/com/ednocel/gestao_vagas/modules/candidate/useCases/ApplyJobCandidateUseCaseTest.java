package br.com.ednocel.gestao_vagas.modules.candidate.useCases;

import br.com.ednocel.gestao_vagas.exceptions.JobNotFoundException;
import br.com.ednocel.gestao_vagas.exceptions.UserNotFoundException;
import br.com.ednocel.gestao_vagas.modules.candidate.entities.ApplyJobEntity;
import br.com.ednocel.gestao_vagas.modules.candidate.entities.CandidateEntity;
import br.com.ednocel.gestao_vagas.modules.candidate.repositories.ApplyJobRepository;
import br.com.ednocel.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import br.com.ednocel.gestao_vagas.modules.company.entities.JobEntity;
import br.com.ednocel.gestao_vagas.modules.company.repositories.JobRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {

    @InjectMocks
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private ApplyJobRepository applyJobRepository;

    @Test
    @DisplayName("Should not be able to apply job with candidate not found")
    public void shouldNotBeAbleToApplyJobWithCandidateNotFound() {
        try {
            applyJobCandidateUseCase.execute(null, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(UserNotFoundException.class);
        }
    }

    @Test
    @DisplayName("Should not be able to apply job with job not found")
    public void shouldNotBeAbleToApplyJobWithJobNotFound() {
        Long idCandidate = 1L;
        var candidate = new CandidateEntity();
        candidate.setId(1L);

        Mockito.when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidate));

        try {
            applyJobCandidateUseCase.execute(idCandidate, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(JobNotFoundException.class);
        }
    }

    @Test
    @DisplayName("Should be able to create a new apply job")
    public void shouldBeAbleToCreateANewApplyJob() {
        var idCandidate = 1L;
        var idJob = 1L;

        var applyJob = ApplyJobEntity.builder()
                .candidateId(idCandidate)
                .jobId(idJob)
                .build();

        var applyJobCreated = ApplyJobEntity.builder().id(2L).build();

        Mockito.when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(new CandidateEntity()));
        Mockito.when(jobRepository.findById(idJob)).thenReturn(Optional.of(new JobEntity()));

        Mockito.when(applyJobRepository.save(applyJob)).thenReturn(applyJobCreated);

        var result = applyJobCandidateUseCase.execute(idCandidate, idJob);

        assertThat(result).hasFieldOrProperty("id");
        assertNotNull(result.getId());
    }


}
