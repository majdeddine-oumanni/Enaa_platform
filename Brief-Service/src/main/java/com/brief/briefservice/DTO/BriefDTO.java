package com.brief.briefservice.DTO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class BriefDTO {
    private String name;
    private String description;
    private List<CompetenceDTO> competenceDTOList;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CompetenceDTO> getCompetenceDTOList() {
        return competenceDTOList;
    }

    public void setCompetenceDTOList(List<CompetenceDTO> competenceDTOList) {
        this.competenceDTOList = competenceDTOList;
    }
}
