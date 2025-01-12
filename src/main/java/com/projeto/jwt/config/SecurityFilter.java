package com.projeto.jwt.config;

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

       if (token != null){

           Optional<String> subject  = tokenService.validateToken(token);

            if (subject.isPresent()){

                final String email = subject.get();

                UserDetails user = usuarioRepository.findByEmail(email)
                        .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado para o e-mail: " + subject));


                final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);

            }
       }
       filterChain.doFilter(request,response);
    }

    private String  recordToken(HttpServletRequest request){
        final String authorization = request.getHeader("Authorization");
        if (authorization == null){
            return null;
        }
        return authorization.replace("Bearer ","");

    }
}
