package com.brief.briefservice.Service;

import com.brief.briefservice.Clients.CompetenceClient;
import com.brief.briefservice.DTO.BriefDTO;
import com.brief.briefservice.DTO.BriefPostDTO;
import com.brief.briefservice.DTO.CompetenceDTO;
import com.brief.briefservice.Mapper.BriefMapper;
import com.brief.briefservice.Mapper.BriefPostMapper;
import com.brief.briefservice.Model.Brief;
import com.brief.briefservice.Repositorie.BriefRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BriefServiceTest {
    private final BriefRepository briefRepository = mock(BriefRepository.class);
    private final BriefMapper briefMapper = mock(BriefMapper.class);
    private final CompetenceClient competenceClient = mock(CompetenceClient.class);
    private final BriefPostMapper briefPostMapper = mock(BriefPostMapper.class);
    private final BriefService service = new BriefService(briefRepository, briefMapper, competenceClient, briefPostMapper);

    @Test
    void addBrief() {
        //enterd dto
        BriefPostDTO postDTO = new BriefPostDTO();
        List<Long> competenceList = new ArrayList<>();
        competenceList.add(1L);
        competenceList.add(2L);
        postDTO.setCompetenceList(competenceList);
        postDTO.setDescription("Application de réservation des événements");
        postDTO.setName("HelloEvents App");

        //returned entity in mapper
        Brief entity = new Brief();
        List<Long> competences = new ArrayList<>();
        competenceList.add(1L);
        competenceList.add(2L);
        entity.setCompetenceList(competences);
        entity.setDescription("Application de réservation des événements");
        entity.setName("HelloEvents App");

        //returned dto
        BriefPostDTO outputDTO = new BriefPostDTO();
        outputDTO.setDescription("Application de réservation des événements");
        outputDTO.setName("HelloEvents App");
        List<Long> competenceList1 = new ArrayList<>();
        competenceList.add(1L);
        competenceList.add(2L);
        outputDTO.setCompetenceList(competenceList1);

        //returned
        Brief savedBrief = new Brief();
        savedBrief.setName("HelloEvents App");
        savedBrief.setDescription("Application de réservation des événements");
        List<Long> competenceList2 = new ArrayList<>();
        competenceList.add(1L);
        competenceList.add(2L);
        savedBrief.setCompetenceList(competenceList2);

        when(briefPostMapper.toEntity(postDTO)).thenReturn(entity);
        when(briefRepository.save(entity)).thenReturn(savedBrief);
        when(briefPostMapper.toDTO(savedBrief)).thenReturn(outputDTO);

        BriefPostDTO result = service.addBrief(postDTO);

        assertEquals(outputDTO.getName(), result.getName());
        assertEquals(outputDTO.getDescription(), result.getDescription());
    }

    @Test
    void getBriefs() {
        // Mock Repository
        Brief brief1 = new Brief();
        brief1.setName("Brief 1");
        brief1.setDescription("Desc 1");
        brief1.setCompetenceList(List.of(1L));

        Brief brief2 = new Brief();
        brief2.setName("Brief 2");
        brief2.setDescription("Desc 2");
        brief2.setCompetenceList(List.of(2L));

        when(briefRepository.findAll()).thenReturn(List.of(brief1, brief2));

        // Mock Mapper
        BriefDTO dto1 = new BriefDTO();
        dto1.setName(brief1.getName());
        dto1.setDescription(brief1.getDescription());

        BriefDTO dto2 = new BriefDTO();
        dto2.setName(brief2.getName());
        dto2.setDescription(brief2.getDescription());

        when(briefMapper.toDTOs(List.of(brief1, brief2))).thenReturn(List.of(dto1, dto2));

        // Test
        List<BriefDTO> result = service.getBriefs();

        // Verify
        assertEquals(2, result.size());
        assertEquals("Brief 1", result.get(0).getName());
        assertEquals("Brief 2", result.get(1).getName());
    }

    @Test
    void getBriefById() {
        // Mock Brief
        Brief mockBrief = new Brief();
        mockBrief.setName("Test Brief");
        mockBrief.setDescription("Test Description");
        mockBrief.setCompetenceList(List.of(1L));
        when(briefRepository.findById(1L)).thenReturn(Optional.of(mockBrief));

        // Mock Mapper
        BriefDTO mockDTO = new BriefDTO();
        mockDTO.setName(mockBrief.getName());
        mockDTO.setDescription(mockBrief.getDescription());
        when(briefMapper.toDTO(mockBrief)).thenReturn(mockDTO);

        // Test
        BriefDTO result = service.getBriefById(1L);

        // Verify
        assertEquals("Test Brief", result.getName());
        assertEquals("Test Description", result.getDescription());
    }

    @Test
    void getBriefWithAllCompetences() {
        // Mock Brief
        Brief mockBrief = new Brief();
        mockBrief.setName("Test Brief");
        mockBrief.setDescription("Test Description");
        mockBrief.setCompetenceList(List.of(1L, 2L));
        when(briefRepository.findById(1L)).thenReturn(Optional.of(mockBrief));

        // Mock Competences
        CompetenceDTO competence1 = new CompetenceDTO();
        competence1.setName("Java");

        CompetenceDTO competence2 = new CompetenceDTO();
        competence2.setName("Spring");

        when(competenceClient.getCompetenceList(List.of(1L, 2L)))
                .thenReturn(List.of(competence1, competence2));

        // Test
        BriefDTO result = service.getBriefWithAllCompetences(1L);

        // Verify
        assertEquals("Test Brief", result.getName());
        assertEquals(2, result.getCompetences().size());
        assertEquals("Java", result.getCompetences().get(0).getName());
    }

    @Test
    void getBriefById_NotFound() {
        when(briefRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> service.getBriefById(99L));
    }
}