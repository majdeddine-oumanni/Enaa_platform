package com.brief.briefservice.repository;

import com.brief.briefservice.model.Brief;
import com.brief.briefservice.service.BriefService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BriefRepository extends JpaRepository<Brief, Long> {
}
