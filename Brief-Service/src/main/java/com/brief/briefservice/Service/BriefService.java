package com.brief.briefservice.Service;

import com.brief.briefservice.DTO.BriefDTO;
import com.brief.briefservice.Mapper.BriefMapper;
import com.brief.briefservice.Model.Brief;
import com.brief.briefservice.Repositorie.BriefRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BriefService {
    private final BriefRepository repository;
    private final BriefMapper mapper;

    public BriefService(BriefRepository repository, BriefMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
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
}
