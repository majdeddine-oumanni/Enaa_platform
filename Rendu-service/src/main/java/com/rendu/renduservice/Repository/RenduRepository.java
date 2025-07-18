package com.rendu.renduservice.Repository;

import com.rendu.renduservice.Model.Rendu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RenduRepository extends JpaRepository<Rendu, Long> {
    List<Rendu> findAllByStudentId(Long id);
}
