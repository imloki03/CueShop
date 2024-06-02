package com.cueshop.repository;

import com.cueshop.model.Cue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CueRepository extends JpaRepository<Cue, Long> {
    Cue findCuesById(Long id);
}
