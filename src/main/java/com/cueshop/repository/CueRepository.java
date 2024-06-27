package com.cueshop.repository;

import com.cueshop.model.Cue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CueRepository extends JpaRepository<Cue, Long> {
    Cue findCuesById(Long id);
    List<Cue> findCuesByNameContainingOrBrandContainingOrSpecificationsContaining(String query, String query1, String query2);
}
