package com.rendu.renduservice.Service;

import com.rendu.renduservice.DTO.BriefDTO;
import com.rendu.renduservice.DTO.RenduDTO;
import com.rendu.renduservice.DTO.RenduGetDTO;
import com.rendu.renduservice.FeignClient.BriefClient;
import com.rendu.renduservice.Mapper.RenduMapper;
import com.rendu.renduservice.Model.Rendu;
import com.rendu.renduservice.Repository.RenduRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RenduServiceTest {

    private final RenduMapper renduMapper = mock(RenduMapper.class);
    private final RenduRepository renduRepository = mock(RenduRepository.class);
    private final BriefClient briefClient = mock(BriefClient.class);
    private final RenduService renduService = new RenduService(renduMapper, renduRepository, briefClient);

    @Test
    void submitRendu() {
        // Input DTO
        RenduDTO inputDTO = new RenduDTO();
        inputDTO.setStudentId(1L);
        inputDTO.setBriefId(1L);
        inputDTO.setUrl("http://example.com/rendu");
        inputDTO.setSubmissionDate(LocalDateTime.now());

        // Entity to be returned by mapper
        Rendu renduEntity = new Rendu();
        renduEntity.setStudentId(inputDTO.getStudentId());
        renduEntity.setBriefId(inputDTO.getBriefId());
        renduEntity.setUrl(inputDTO.getUrl());
        renduEntity.setSubmissionDate(inputDTO.getSubmissionDate());

        // Saved entity to be returned by repository
        Rendu savedRendu = new Rendu();
        savedRendu.setId(1L);
        savedRendu.setStudentId(inputDTO.getStudentId());
        savedRendu.setBriefId(inputDTO.getBriefId());
        savedRendu.setUrl(inputDTO.getUrl());
        savedRendu.setSubmissionDate(inputDTO.getSubmissionDate());

        // Output DTO to be returned by mapper
        RenduDTO outputDTO = new RenduDTO();
        outputDTO.setStudentId(inputDTO.getStudentId());
        outputDTO.setBriefId(inputDTO.getBriefId());
        outputDTO.setUrl(inputDTO.getUrl());
        outputDTO.setSubmissionDate(inputDTO.getSubmissionDate());

        // Mocking
        when(renduMapper.toEntity(inputDTO)).thenReturn(renduEntity);
        when(renduRepository.save(renduEntity)).thenReturn(savedRendu);
        when(renduMapper.toDTO(savedRendu)).thenReturn(outputDTO);

        // Test
        RenduDTO result = renduService.submitRendu(inputDTO);

        // Verify
        assertEquals(outputDTO.getStudentId(), result.getStudentId());
        assertEquals(outputDTO.getBriefId(), result.getBriefId());
        assertEquals(outputDTO.getUrl(), result.getUrl());
        assertEquals(outputDTO.getSubmissionDate(), result.getSubmissionDate());
    }

    @Test
    void getRenduListByStudentId() {
        // Mock student ID
        Long studentId = 1L;

        // Mock Rendu entities from repository
        Rendu rendu1 = new Rendu();
        rendu1.setId(1L);
        rendu1.setStudentId(studentId);
        rendu1.setBriefId(1L);
        rendu1.setUrl("http://example.com/rendu1");
        rendu1.setSubmissionDate(LocalDateTime.now().minusDays(1));

        Rendu rendu2 = new Rendu();
        rendu2.setId(2L);
        rendu2.setStudentId(studentId);
        rendu2.setBriefId(2L);
        rendu2.setUrl("http://example.com/rendu2");
        rendu2.setSubmissionDate(LocalDateTime.now());

        when(renduRepository.findAllByStudentId(studentId)).thenReturn(List.of(rendu1, rendu2));

        // Mock BriefDTOs from briefClient
        BriefDTO briefDTO1 = new BriefDTO();
        briefDTO1.setName("Brief 1");
        briefDTO1.setDescription("Description 1");

        BriefDTO briefDTO2 = new BriefDTO();
        briefDTO2.setName("Brief 2");
        briefDTO2.setDescription("Description 2");

        when(briefClient.getBriefById(1L)).thenReturn(briefDTO1);
        when(briefClient.getBriefById(2L)).thenReturn(briefDTO2);

        // Expected RenduGetDTOs
        RenduGetDTO expectedDTO1 = new RenduGetDTO();
        expectedDTO1.setStudentId(studentId);
        expectedDTO1.setSubmissionDate(rendu1.getSubmissionDate());
        expectedDTO1.setBriefDTO(briefDTO1);
        expectedDTO1.setUrl(rendu1.getUrl());

        RenduGetDTO expectedDTO2 = new RenduGetDTO();
        expectedDTO2.setStudentId(studentId);
        expectedDTO2.setSubmissionDate(rendu2.getSubmissionDate());
        expectedDTO2.setBriefDTO(briefDTO2);
        expectedDTO2.setUrl(rendu2.getUrl());

        // Test
        List<RenduGetDTO> result = renduService.getRenduListByStudentId(studentId);

        // Verify
        assertEquals(2, result.size());
        assertEquals(studentId, result.get(0).getStudentId());
        assertEquals("Brief 1", result.get(0).getBriefDTO().getName());
        assertEquals("http://example.com/rendu1", result.get(0).getUrl());
        assertEquals("Brief 2", result.get(1).getBriefDTO().getName());
        assertEquals("http://example.com/rendu2", result.get(1).getUrl());
    }

    @Test
    void getBriefIDs() {
        // Mock dates
        LocalDateTime date1 = LocalDateTime.now().minusDays(7);
        LocalDateTime date2 = LocalDateTime.now();

        // Mock repository response
        List<Long> briefIds = List.of(1L, 2L, 3L);
        when(renduRepository.findBySubmissionDate(date1, date2)).thenReturn(briefIds);

        // Mock BriefClient responses
        BriefDTO briefDTO1 = new BriefDTO();
        briefDTO1.setName("Brief 1");

        BriefDTO briefDTO2 = new BriefDTO();
        briefDTO2.setName("Brief 2");

        BriefDTO briefDTO3 = new BriefDTO();
        briefDTO3.setName("Brief 3");

        when(briefClient.getBriefById(1L)).thenReturn(briefDTO1);
        when(briefClient.getBriefById(2L)).thenReturn(briefDTO2);
        when(briefClient.getBriefById(3L)).thenReturn(briefDTO3);

        // Test
        List<BriefDTO> result = renduService.getBriefIDs(date1, date2);

        // Verify
        assertEquals(3, result.size());
        assertEquals("Brief 1", result.get(0).getName());
        assertEquals("Brief 2", result.get(1).getName());
        assertEquals("Brief 3", result.get(2).getName());
    }

    @Test
    void getBriefIDs_NoResults() {
        // Mock dates
        LocalDateTime date1 = LocalDateTime.now().minusDays(7);
        LocalDateTime date2 = LocalDateTime.now();

        // Mock empty repository response
        when(renduRepository.findBySubmissionDate(date1, date2)).thenReturn(new ArrayList<>());

        // Test
        List<BriefDTO> result = renduService.getBriefIDs(date1, date2);

        // Verify
        assertTrue(result.isEmpty());
    }
}