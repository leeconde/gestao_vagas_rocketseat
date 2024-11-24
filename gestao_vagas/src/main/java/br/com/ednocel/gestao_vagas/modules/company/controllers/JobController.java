package br.com.ednocel.gestao_vagas.modules.company.controllers;

import br.com.ednocel.gestao_vagas.modules.company.dto.CreateJobDto;
import br.com.ednocel.gestao_vagas.modules.company.entities.JobEntity;
import br.com.ednocel.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company/job")
public class JobController {

    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping("/")
    @PreAuthorize("hasRole('COMPANY')")
    public JobEntity create(@Valid @RequestBody CreateJobDto createJobDto, HttpServletRequest request) {
        String companyIdString = String.valueOf(request.getAttribute("company_id"));

        var jobEntity = JobEntity.builder()
                .benefits(createJobDto.getBenefits())
                .companyId(Long.parseLong(companyIdString))
                .description(createJobDto.getDescription())
                .level(createJobDto.getLevel())
                .build();

        return this.createJobUseCase.execute(jobEntity);
    }
}
