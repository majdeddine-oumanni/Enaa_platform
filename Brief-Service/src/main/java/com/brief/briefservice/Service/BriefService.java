package com.brief.briefservice.Service;

import com.brief.briefservice.Clients.CompetenceClient;
import com.brief.briefservice.DTO.BriefDTO;
import com.brief.briefservice.DTO.CompetenceDTO;
import com.brief.briefservice.Mapper.BriefMapper;
import com.brief.briefservice.Model.Brief;
import com.brief.briefservice.Repositorie.BriefRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class BriefService {
    private final BriefRepository repository;
    private final BriefMapper mapper;
    private final CompetenceClient competenceClient;

    public BriefService(BriefRepository repository, BriefMapper mapper, CompetenceClient competenceClient) {
        this.repository = repository;
        this.mapper = mapper;
        this.competenceClient = competenceClient;
    }

    public BriefDTO addBrief(BriefDTO dto){
        Brief brief = mapper.toEntity(dto);
        Brief savedBrief = repository.save(brief);
        return mapper.toDTO(savedBrief);
    }

    public List<BriefDTO> getBriefs(){
        List<Brief> briefList = repository.findAll();
        return mapper.toDTOs(briefList);
    }


    public BriefDTO getBriefWithAllCompetences(Long id){
        Brief brief = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("brief not found"));
        List<CompetenceDTO> competenceList = competenceClient.getCompetenceList();
        BriefDTO dto = new BriefDTO();
        dto.setCompetenceDTOList(competenceList);
        dto.setDescription(brief.getDescription());
        dto.setName(brief.getName());
        return dto;
    }
}
