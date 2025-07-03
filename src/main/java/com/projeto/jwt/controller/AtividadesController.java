package com.projeto.jwt.controller;

import com.projeto.jwt.config.SecurityConfig;
import com.projeto.jwt.dto.request.AtividadesRequestDTO;
import com.projeto.jwt.dto.response.AtividadeListResponseDTO;
import com.projeto.jwt.dto.response.AtividadeResponseDTO;
import com.projeto.jwt.enums.Status;
import com.projeto.jwt.model.Usuario;
import com.projeto.jwt.service.AtividadeService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/atividades")
@AllArgsConstructor
@Slf4j
@SecurityRequirement(name = SecurityConfig.SECURITY)
public class AtividadesController {

    private final AtividadeService atividadeService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AtividadeResponseDTO> created(@RequestBody @Valid AtividadesRequestDTO atividades, @AuthenticationPrincipal Usuario usuario) throws UserPrincipalNotFoundException {
        return ResponseEntity.created(null).body(atividadeService.create(atividades,usuario));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Page<AtividadeResponseDTO>> find(@Parameter(hidden = true) Pageable paginacao){

        try{

            return ResponseEntity.ok().body(atividadeService.find(paginacao));

        } catch (Exception e) {

            log.error("Erro ao listar Atividades.", e);
            return  ResponseEntity.badRequest().body(Page.empty());
        }

    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<AtividadeListResponseDTO> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(atividadeService.readById(id));

    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<AtividadeResponseDTO> update(@PathVariable Long id, @RequestBody AtividadesRequestDTO atividades) {
        return ResponseEntity.ok().body(atividadeService.update(atividades,id).getBody());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity<AtividadeResponseDTO> delete(@PathVariable  Long id) {

        if (atividadeService.deleteById(id)) {

            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();

    }

    @GetMapping("/statusx/{status}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<List<AtividadeResponseDTO>>findStatus(@PathVariable("status") Status status){
            try{

                return  ResponseEntity.ok(atividadeService.findByStatus(status));

            }catch (Exception e) {

                log.error("Erro ao listar atividades por status.", e);
                return ResponseEntity.badRequest().body(List.of());
            }

    }
}
