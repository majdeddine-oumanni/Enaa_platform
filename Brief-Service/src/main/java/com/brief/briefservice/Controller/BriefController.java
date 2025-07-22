package com.brief.briefservice.Controller;

import com.brief.briefservice.DTO.BriefDTO;
import com.brief.briefservice.DTO.BriefPostDTO;
import com.brief.briefservice.Service.BriefService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class BriefController {
    private final BriefService service;

    public BriefController(BriefService service) {
        this.service = service;
    }
    @PostMapping("/add")
    public BriefPostDTO addBrief(@RequestBody BriefPostDTO dto){
        return service.addBrief(dto);
    }

    @GetMapping("/getAll")
    public List<BriefDTO> getBriefList(){
        return service.getBriefs();
    }

    @GetMapping("/{id}")
    public BriefDTO getBrief(@PathVariable Long id){
        return service.getBriefWithAllCompetences(id);
    }

    @GetMapping("/test")
    public String test(){
        return "working";
    }
}
