package com.projeto.jwt.dto.userDTO;

import com.projeto.jwt.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterDTO(
                          String nome,
                          @NotBlank(message = "O email é obrigatório")
                          @Email(message = "Formato de email inválido")
                          String email,
                          String password ,
                          UserRole role ) {
}
