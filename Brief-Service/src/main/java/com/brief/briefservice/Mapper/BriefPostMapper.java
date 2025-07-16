package com.brief.briefservice.Mapper;

import com.brief.briefservice.DTO.BriefPostDTO;
import com.brief.briefservice.Model.Brief;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BriefPostMapper {
    BriefPostDTO toDTO(Brief brief);
    Brief toEntity(BriefPostDTO dto);
}
