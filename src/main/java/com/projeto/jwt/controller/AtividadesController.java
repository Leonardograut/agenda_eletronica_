package com.projeto.jwt.controller;

import com.projeto.jwt.dto.request.AtividadesRequestDTO;
import com.projeto.jwt.dto.response.AtividadeResponseDTO;
import com.projeto.jwt.service.AtividadeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/atividades")
@AllArgsConstructor
public class AtividadesController {


    private final AtividadeService atividadeService;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AtividadeResponseDTO> created(@RequestBody AtividadesRequestDTO atividades){
        final  AtividadeResponseDTO responseDTO = atividadeService.create(atividades);
        return ResponseEntity.ok(responseDTO);
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<AtividadeResponseDTO>> find(@PageableDefault(size = 10) Pageable paginacao){
        final Page<AtividadeResponseDTO> responseDTOS = atividadeService.find(paginacao);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTOS);
    }


    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AtividadeResponseDTO> getById(@PathVariable("id") Long id){
        final AtividadeResponseDTO dto = atividadeService.readById(id);
        return ResponseEntity.ok(dto);

    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AtividadeResponseDTO> update(@PathVariable Long id, @RequestBody AtividadesRequestDTO atividades) {
        final AtividadeResponseDTO atividadeResponseDTO =atividadeService.update(atividades,id).getBody();
        return ResponseEntity.status(HttpStatus.OK).body(atividadeResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AtividadeResponseDTO> delete(@PathVariable  Long id) {
        if (atividadeService.deleteById(id)) {

            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();

    }
}
