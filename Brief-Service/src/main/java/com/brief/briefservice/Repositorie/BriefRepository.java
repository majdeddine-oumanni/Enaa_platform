package com.brief.briefservice.Repositorie;

import com.brief.briefservice.Model.Brief;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BriefRepository extends JpaRepository<Brief, Long> {
}
