package com.brief.briefservice.controller;

import com.brief.briefservice.dto.BriefDto;
import com.brief.briefservice.service.BriefService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController

public class BriefController {

private BriefService briefService;


@PostMapping("/brief")
    public BriefDto post(@RequestBody BriefDto dto){
        return briefService.post(dto);
    }






}
