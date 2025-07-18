package com.rendu.renduservice.DTO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
public class RenduGetDTO {
    private Long studentId;
    private BriefDTO briefDTO;
    private List<CompetenceDTO> competenceDTO;
    private String url;
    private LocalDateTime submissionDate;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public BriefDTO getBriefDTO() {
        return briefDTO;
    }

    public void setBriefDTO(BriefDTO briefDTO) {
        this.briefDTO = briefDTO;
    }

    public List<CompetenceDTO> getCompetenceDTO() {
        return competenceDTO;
    }

    public void setCompetenceDTO(List<CompetenceDTO> competenceDTO) {
        this.competenceDTO = competenceDTO;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDateTime getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDateTime submissionDate) {
        this.submissionDate = submissionDate;
    }
}
