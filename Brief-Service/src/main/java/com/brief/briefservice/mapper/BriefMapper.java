package com.brief.briefservice.mapper;

import com.brief.briefservice.dto.BriefDto;
import com.brief.briefservice.model.Brief;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface BriefMapper {



    Brief toEntity(BriefDto dto);
    BriefDto toDTO(Brief brief);
    List<BriefDto> toDTOs(List<Brief> competenceList);
}
