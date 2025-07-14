package com.aprenant.aprenantservice.Service;

import com.aprenant.aprenantservice.DTO.BriefDTO;
import com.aprenant.aprenantservice.DTO.StudentDTO;
import com.aprenant.aprenantservice.FeignClientCalls.BriefCall;
import com.aprenant.aprenantservice.Mapper.StudentMapper;
import com.aprenant.aprenantservice.Model.Student;
import com.aprenant.aprenantservice.Repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final BriefCall briefCall;
    private final StudentMapper mapper;
    private final StudentRepository repository;

    public StudentService(BriefCall briefCall, StudentMapper mapper, StudentRepository repository) {
        this.briefCall = briefCall;
        this.mapper = mapper;
        this.repository = repository;
    }

    public List<BriefDTO> fetchBriefList(){
        return briefCall.getBriefList();
    }

    public StudentDTO addStudent(StudentDTO dto){
        Student student = mapper.toEntity(dto);
        Student savedStudent = repository.save(student);
        return mapper.toDTO(savedStudent);
    }

    public List<StudentDTO> getStudentList(){
        List<Student> students = repository.findAll();
        return mapper.toDTOs(students);
    }
}
