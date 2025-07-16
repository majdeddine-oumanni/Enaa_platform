package com.brief.briefservice.Clients;

import com.brief.briefservice.DTO.CompetenceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;
@FeignClient(name = "backend", url = "http://localhost:8080/competence")
public interface CompetenceClient {
    @GetMapping("/getListByIDs")
    List<CompetenceDTO> getCompetenceList(@RequestParam("ids") List<Long> ids);
}
