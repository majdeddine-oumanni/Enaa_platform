package com.rendu.renduservice.FeignClient;

import com.rendu.renduservice.DTO.BriefDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Brief-Service", url = "http://localhost:8081/brief")
public interface BriefClient {
    @GetMapping("/{id}")
    BriefDTO getBriefById(@PathVariable Long id);
}
