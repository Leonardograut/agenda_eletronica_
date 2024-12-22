package com.projeto.jwt.service;

import com.projeto.jwt.dto.request.AtividadesRequestDTO;
import com.projeto.jwt.dto.response.AtividadeListResponseDTO;
import com.projeto.jwt.dto.response.AtividadeResponseDTO;
import com.projeto.jwt.enums.Status;
import com.projeto.jwt.exceptions.AtividadesNotFoundException;
import com.projeto.jwt.model.Atividades;
import com.projeto.jwt.repository.AtividadeRepository;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AtividadeService {

    private AtividadeRepository atividadeRepository;

    private final ModelMapper mapper = new ModelMapper();

    public AtividadeResponseDTO create(AtividadesRequestDTO atividades  ) {

       Atividades atividades1 = mapper.map(atividades,Atividades.class);

        atividadeRepository.save(atividades1);

        return mapper.map(atividades1,AtividadeResponseDTO.class);

    }

    public Page<AtividadeResponseDTO> find(Pageable paginacao) {


        if (paginacao == null || paginacao.getPageNumber() < 0 || paginacao.getPageSize() <= 0) {
            throw new IllegalArgumentException("Paginação inválida!");
        }

        return atividadeRepository.
                findAll(paginacao).map(e -> mapper.map(e, AtividadeResponseDTO.class));

    }

    public AtividadeListResponseDTO readById(Long id) {


        final Atividades record = atividadeRepository.findById(id)
                .orElseThrow(() -> new AtividadesNotFoundException("Atividade nao encontrada"));

        record.setQtdViews(record.getQtdViews() +1);

        atividadeRepository.save(record);

        return mapper.map(record,AtividadeListResponseDTO.class);
    }

    public ResponseEntity<AtividadeResponseDTO> update(AtividadesRequestDTO atividades, Long id) {

        return atividadeRepository.findById(id)
                .map(p -> {
                    p.setNome(atividades.getNome());
                    p.setDescricao(atividades.getDescricao());
                    p.setDataHoraInicio(atividades.getDataHoraInicio());
                    p.setDataHoraTermino(atividades.getDataHoraTermino());
                    p.setStatus(atividades.getStatus());
                    final Atividades save = atividadeRepository.save(p);
                    final AtividadeResponseDTO responseDTO = mapper.map(save,AtividadeResponseDTO.class);
                    return ResponseEntity.ok().body(responseDTO);

                }).orElseThrow(()-> new AtividadesNotFoundException("Atividade nao encontrada"));

    }

    public boolean deleteById(Long id) {
        return atividadeRepository.findById(id)
                .map(delete -> {
                    atividadeRepository.deleteById(id);
                    return true;
                })
                .orElseThrow(()-> new AtividadesNotFoundException("Atividade nao encontrada"));

    }

    public List<AtividadeResponseDTO> findByStatus(Status status) throws  AtividadesNotFoundException{
        List<Atividades> atividade = atividadeRepository.findByStatus(status);

        if (atividade.isEmpty()) {
            throw new AtividadesNotFoundException("Nenhuma atividade encontrada com o status: " + status);
        }

        return atividade.stream()
                .map(atividades -> mapper.map(atividades, AtividadeResponseDTO.class))
                .collect(Collectors.toList());
    }


}
