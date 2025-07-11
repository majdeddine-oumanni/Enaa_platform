package com.brief.briefservice.service;

import com.brief.briefservice.dto.BriefDto;
import com.brief.briefservice.mapper.BriefMapper;
import com.brief.briefservice.model.Brief;
import com.brief.briefservice.repository.BriefRepository;
import org.springframework.stereotype.Service;


@Service
public class BriefService {

    private BriefRepository briefRepository;
    private BriefMapper briefMapper;

    public BriefService(BriefRepository briefRepository, BriefMapper briefMapper) {
        this.briefRepository = briefRepository;
        this.briefMapper = briefMapper;
    }

    public BriefDto post (BriefDto dto){
        Brief brief = briefMapper.toEntity(dto);
        Brief savedBrief = briefRepository.save(brief);
        return briefMapper.toDTO(savedBrief);
    }











}
