package com.projeto.jwt.dto.response;

import com.projeto.jwt.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtividadeResponseDTO {

    private Long id;

    private String nome;

    private String descricao;

    private LocalDateTime dataHoraInicio;

    private LocalDateTime dataHoraTermino;

    @Enumerated(EnumType.STRING)
    private Status status;


}
