package com.rendu.renduservice.Repository;

import com.rendu.renduservice.Model.Rendu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface RenduRepository extends JpaRepository<Rendu, Long> {
    List<Rendu> findAllByStudentId(Long id);

    @Query(value = "SELECT DISTINCT  idbrief from Rendu where submissionDate between :Date1 and :Date2",nativeQuery = true)

    List<Long> findBySubmissionDate(LocalDateTime Date1 , LocalDateTime Date2);
}
