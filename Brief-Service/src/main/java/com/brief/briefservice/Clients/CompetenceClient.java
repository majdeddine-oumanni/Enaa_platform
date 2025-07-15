package com.brief.briefservice.Clients;

import com.brief.briefservice.DTO.CompetenceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

@FeignClient(name = "backend", url = "http://localhost:8080/competence")
public interface CompetenceClient {
    @GetMapping("/getAll")
    List<CompetenceDTO> getCompetenceList();
}
