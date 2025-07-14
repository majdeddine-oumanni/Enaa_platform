package com.brief.briefservice.Controller;

import com.brief.briefservice.DTO.BriefDTO;
import com.brief.briefservice.Model.Brief;
import com.brief.briefservice.Service.BriefService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BriefController {
    private final BriefService service;

    public BriefController(BriefService service) {
        this.service = service;
    }
    @PostMapping("/add")
    public BriefDTO addBrief(@RequestBody BriefDTO dto){
        return service.addBrief(dto);
    }
    @GetMapping("/getAll")
    public List<BriefDTO> getBriefList(){
        return service.getBriefs();
    }
}
