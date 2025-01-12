package com.projeto.jwt.controller;

import com.projeto.jwt.config.TokenService;
import com.projeto.jwt.dto.userDTO.AuthenticationRequestDTO;
import com.projeto.jwt.dto.userDTO.RegisterDTO;
import com.projeto.jwt.model.Usuario;
import com.projeto.jwt.repository.UsuarioRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final UsuarioRepository usuarioRepository;

    private final TokenService tokenService;


    @PostMapping ("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationRequestDTO usuario){

            final UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(usuario.email(), usuario.password());

            final Authentication auth = this.authenticationManager.authenticate(usernamePassword);

            final String token = tokenService.gerarToken((Usuario) auth.getPrincipal());

            return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @PostMapping ("/register")
    public  ResponseEntity register (@RequestBody @Valid RegisterDTO dto){

        Optional<Usuario> existingUser = this.usuarioRepository.findByEmail(dto.email());
        if (existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        final String passwordEncoder = new BCryptPasswordEncoder().encode(dto.password());

        Usuario newUser = new Usuario(dto.nome(), dto.email(), passwordEncoder, dto.role());

        this.usuarioRepository.save(newUser);

        return ResponseEntity.status(HttpStatus.OK).build();

    }

}
