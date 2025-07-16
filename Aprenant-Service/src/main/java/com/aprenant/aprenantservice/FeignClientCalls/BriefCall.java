package com.aprenant.aprenantservice.FeignClientCalls;

import com.aprenant.aprenantservice.DTO.BriefDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "Brief-Service", url = "http://localhost:8081/brief")
public interface BriefCall {
    @GetMapping("/getAll")
    List<BriefDTO> getBriefList();

    @GetMapping("/competences/{id}")
    BriefDTO getBriefWithCompetences();
}
