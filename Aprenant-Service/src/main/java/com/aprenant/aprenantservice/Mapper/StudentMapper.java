package com.aprenant.aprenantservice.Mapper;

import com.aprenant.aprenantservice.DTO.StudentDTO;
import com.aprenant.aprenantservice.Model.Student;
import org.mapstruct.Mapper;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    Student toEntity(StudentDTO dto);
    StudentDTO toDTO(Student student);
    List<StudentDTO> toDTOs(List<Student> students);
}
