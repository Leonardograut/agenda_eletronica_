package com.projeto.jwt.dto.request;
import com.projeto.jwt.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtividadesRequestDTO {

    @Column(name = "nome",nullable = false,columnDefinition = "VARCHAR(100)")
    private String nome;

    @Column(name = "descricao",nullable = false,columnDefinition = "VARCHAR(255)")
    private String descricao;


    private LocalDateTime dataHoraInicio;

    private LocalDateTime dataHoraTermino;

    @Enumerated(EnumType.STRING)
    private Status status;


}
