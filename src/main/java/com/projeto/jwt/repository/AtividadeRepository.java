package com.projeto.jwt.repository;

import com.projeto.jwt.enums.Status;
import com.projeto.jwt.model.Atividades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AtividadeRepository   extends JpaRepository<Atividades,Long> {

    @Query("SELECT p FROM Atividades p WHERE p.status = :status")
    List<Atividades> findByStatus(@Param("status") Status status);



}

