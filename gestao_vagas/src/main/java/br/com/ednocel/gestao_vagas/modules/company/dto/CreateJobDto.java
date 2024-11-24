package br.com.ednocel.gestao_vagas.modules.company.dto;

import lombok.Data;

@Data
public class CreateJobDto {
    private String description;
    private String benefits;
    private String level;

}
