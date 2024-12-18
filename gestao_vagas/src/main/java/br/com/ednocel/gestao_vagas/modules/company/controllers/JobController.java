package br.com.ednocel.gestao_vagas.modules.company.controllers;

import br.com.ednocel.gestao_vagas.modules.company.dto.CreateJobDto;
import br.com.ednocel.gestao_vagas.modules.company.entities.JobEntity;
import br.com.ednocel.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @Tag(name = "Vagas", description = "Informações das vagas")
    @Operation(summary = "Cadastro de vagas.",
            description = "Essa função é responsavel por cadastrar as vagas na empresa")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(
                            schema = @Schema(implementation = JobEntity.class)
                    )
            })
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> create(@Valid @RequestBody CreateJobDto createJobDto, HttpServletRequest request) {
        String companyIdString = String.valueOf(request.getAttribute("company_id"));

        try {
            var jobEntity = JobEntity.builder()
                    .benefits(createJobDto.getBenefits())
                    .companyId(Long.parseLong(companyIdString))
                    .description(createJobDto.getDescription())
                    .level(createJobDto.getLevel())
                    .build();

            var result = this.createJobUseCase.execute(jobEntity);

            return ResponseEntity.ok(result);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
