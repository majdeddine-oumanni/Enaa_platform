package com.rendu.renduservice.Controller;

import com.rendu.renduservice.DTO.BriefDTO;
import com.rendu.renduservice.DTO.RenduDTO;
import com.rendu.renduservice.DTO.RenduGetDTO;
import com.rendu.renduservice.Service.RenduService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
//@RequestMapping("/rendu")
public class RenduController {
    private final RenduService service;

    public RenduController(RenduService service) {
        this.service = service;
    }

    @PostMapping("/post")
    public RenduDTO postRendu(@RequestBody RenduDTO dto){
        return service.submitRendu(dto);
    }

    @GetMapping("/get/{student_id}")
    public List<RenduGetDTO> getRenduListByStudentId(@PathVariable Long student_id){
        return service.getRenduListByStudentId(student_id);
    }
    @GetMapping("/getbydate/{Date1}/{Date2}")
    public List<BriefDTO> findbydate (@RequestParam LocalDateTime Date1 , @RequestParam LocalDateTime Date2){
        return service.getBriefIDs(Date1,Date2);
    }
}
