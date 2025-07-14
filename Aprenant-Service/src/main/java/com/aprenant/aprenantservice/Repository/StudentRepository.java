package com.aprenant.aprenantservice.Repository;

import com.aprenant.aprenantservice.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
