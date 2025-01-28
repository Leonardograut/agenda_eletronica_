package com.projeto.jwt.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projeto.jwt.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "atividades")
public class Atividades {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, columnDefinition = "VARCHAR(100)")
    private String nome;

    @Column(name = "descricao", nullable = false, columnDefinition = "VARCHAR(255)")
    private String descricao;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS")
    @Column(name = "data_hora_inicio")
    private LocalDateTime dataHoraInicio;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS")
    @Column(name = "data_hora_termino")
    private LocalDateTime dataHoraTermino;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false , length = 50)
    private Status status;

    @Column(name = "qtd_views")
    private int QtdViews;


    @ManyToOne(optional = false)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario" , foreignKey = @ForeignKey(name = "fk_atividades_usuarios"))
    private Usuario usuario;


}
