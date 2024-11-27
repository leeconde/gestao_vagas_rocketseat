package br.com.ednocel.gestao_vagas.modules.candidate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCandidateResponseDTO {

    private Long id;

    @Schema(example = "Maria Souza")
    private String name;

    @Schema(example = "maria.souza")
    private String username;

    @Schema(example = "maria@gmail.com")
    private String email;

    @Schema(example = "Desenvolvedora Java")
    private String description;
}
