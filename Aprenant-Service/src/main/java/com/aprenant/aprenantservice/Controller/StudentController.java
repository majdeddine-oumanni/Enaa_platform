package com.aprenant.aprenantservice.Controller;

import com.aprenant.aprenantservice.DTO.BriefDTO;
import com.aprenant.aprenantservice.DTO.StudentDTO;
import com.aprenant.aprenantservice.Service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping("/briefs")
    public List<BriefDTO> fetchBriefList(){
        return service.fetchBriefList();
    }

    @PostMapping("/post")
    public StudentDTO addStudent(@RequestBody StudentDTO dto){
        return service.addStudent(dto);
    }
    @GetMapping("/getAll")
    public List<StudentDTO> getStudents(){
        return service.getStudentList();
    }
}
