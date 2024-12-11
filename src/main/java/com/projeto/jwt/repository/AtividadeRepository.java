package com.projeto.jwt.repository;

import com.projeto.jwt.model.Atividades;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtividadeRepository   extends JpaRepository<Atividades,Long> {
}
