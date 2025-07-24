package com.rendu.renduservice.Service;

import com.rendu.renduservice.DTO.BriefDTO;
import com.rendu.renduservice.DTO.RenduDTO;
import com.rendu.renduservice.DTO.RenduGetDTO;
import com.rendu.renduservice.FeignClient.BriefClient;
import com.rendu.renduservice.Mapper.RenduMapper;
import com.rendu.renduservice.Model.Rendu;
import com.rendu.renduservice.Repository.RenduRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Service
public class RenduService {
    private final RenduMapper mapper;
    private final RenduRepository repository;
    private final BriefClient briefClient;

    public RenduService(RenduMapper mapper, RenduRepository repository, BriefClient briefClient) {
        this.mapper = mapper;
        this.repository = repository;
        this.briefClient = briefClient;
    }

    public RenduDTO submitRendu(RenduDTO dto){
        Rendu rendu = mapper.toEntity(dto);
        Rendu savedRendu = repository.save(rendu);
        return mapper.toDTO(savedRendu);
    }

    public List<RenduGetDTO> getRenduListByStudentId(Long studentId) {
        List<Rendu> renduList = repository.findAllByStudentId(studentId);

        return renduList.stream().map(rendu -> {
            BriefDTO briefDTO = briefClient.getBriefById(rendu.getBriefId());

            RenduGetDTO dto = new RenduGetDTO();
            dto.setStudentId(studentId);
            dto.setSubmissionDate(rendu.getSubmissionDate());
            dto.setBriefDTO(briefDTO);
            dto.setCompetenceDTO(briefDTO.getCompetences());
            dto.setUrl(rendu.getUrl());

            return dto;
        }).collect(Collectors.toList());
    }
    public List<BriefDTO> getBriefIDs(LocalDateTime date1, LocalDateTime date2){
        List<Long> briefIDs = repository.findBySubmissionDate(date1, date2);
        List<BriefDTO> briefDTOS = new ArrayList<>();
        briefIDs.forEach((id)->{
            briefDTOS.add(briefClient.getBriefById(id));
        });
        return briefDTOS;
    }

}
