package br.com.ednocel.gestao_vagas.modules.candidate.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Data
@Entity(name = "candidate")
public class CandidateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(example = "Joao Pedro")
    private String name;

    @NotBlank
    @Pattern(regexp = "\\S+", message = "O campo username não deve conter espaço")
    @Schema(example = "jpedro")
    private String username;

    @Email(message = "O campo email deve conter um e-mail válido")
    @Schema(example = "jpedro@gmail.com")
    private String email;

    @Length(min = 10, max = 100, message = "A senha deve conter entre 10 e 100 caracteres")
    @Schema(example = "Admin@1234", minLength = 10, maxLength = 100)
    private String password;

    @Schema(example = "Desenvolvedor Java Pleno")
    private String description;

    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
