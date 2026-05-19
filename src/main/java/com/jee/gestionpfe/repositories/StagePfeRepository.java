package com.jee.gestionpfe.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jee.gestionpfe.entities.StagePfe;

public interface StagePfeRepository extends JpaRepository<StagePfe, Long> {

    List<StagePfe> findByAnnee(Integer annee);

    List<StagePfe> findByEtudiantFiliereId(Long filiereId);
}
