package com.projeto.jwt.config;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.projeto.jwt.model.Usuario;
import com.projeto.jwt.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;


@Component
@AllArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private TokenService tokenService;

    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String token = this.recordToken(request);

        Optional.ofNullable(token).ifPresent(t -> {
            Optional<String> subject = tokenService.validateToken(t);

            subject.ifPresent(email -> {
                UserDetails user = usuarioRepository.findByEmail(email)

                        .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado para o e-mail: " + email));

                final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            });
        });

        filterChain.doFilter(request, response);
    }

    private String  recordToken(HttpServletRequest request){
        final String authorization = request.getHeader("Authorization");

        return Optional.ofNullable(authorization)
                .map(auth -> auth.replace("Bearer ","")).orElse("");
   }
}
