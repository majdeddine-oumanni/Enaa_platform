package com.brief.briefservice.Service;

import com.brief.briefservice.Clients.CompetenceClient;
import com.brief.briefservice.DTO.BriefDTO;
import com.brief.briefservice.DTO.BriefPostDTO;
import com.brief.briefservice.DTO.CompetenceDTO;
import com.brief.briefservice.Mapper.BriefMapper;
import com.brief.briefservice.Mapper.BriefPostMapper;
import com.brief.briefservice.Model.Brief;
import com.brief.briefservice.Repositorie.BriefRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BriefService {
    private final BriefRepository repository;
    private final BriefMapper mapper;
    private final CompetenceClient competenceClient;
    private final BriefPostMapper briefPostMapper;

    public BriefService(BriefRepository repository, BriefMapper mapper, CompetenceClient competenceClient, BriefPostMapper briefPostMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.competenceClient = competenceClient;
        this.briefPostMapper = briefPostMapper;
    }

    public BriefPostDTO addBrief(BriefPostDTO dto){
        Brief brief = briefPostMapper.toEntity(dto);
        Brief saved = repository.save(brief);
        return briefPostMapper.toDTO(saved);
    }


    public List<BriefDTO> getBriefs(){
        List<Brief> briefList = repository.findAll();
        return mapper.toDTOs(briefList);
    }

    public BriefDTO getBriefById(Long id){
        Brief brief = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("brief not found"));
        return mapper.toDTO(brief);
    }


    public BriefDTO getBriefWithAllCompetences(Long id){
        Brief brief = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("brief not found"));

        List<CompetenceDTO> competenceList = competenceClient.getCompetenceList(brief.getCompetenceList());

        BriefDTO dto = new BriefDTO();
        dto.setCompetences(competenceList);
        dto.setDescription(brief.getDescription());
        dto.setName(brief.getName());

        return dto;
    }


}
