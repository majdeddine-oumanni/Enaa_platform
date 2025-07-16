package com.brief.briefservice.DTO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class BriefPostDTO {
    private String name;
    private String description;
    private List<Long> competenceList;

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

    public List<Long> getCompetenceList() {
        return competenceList;
    }

    public void setCompetenceList(List<Long> competenceList) {
        this.competenceList = competenceList;
    }
}
