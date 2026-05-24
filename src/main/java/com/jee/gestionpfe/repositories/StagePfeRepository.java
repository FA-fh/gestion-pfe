package com.jee.gestionpfe.repositories;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import com.jee.gestionpfe.entities.StagePfe;
import org.springframework.data.jpa.repository.Query;

public interface StagePfeRepository extends JpaRepository<StagePfe, Long> {

    List<StagePfe> findByAnnee(Integer annee);

    List<StagePfe> findByEtudiantFiliereId(Long filiereId);
    
    List<StagePfe> findByEtudiantFiliereIdAndAnnee(Long filiereId, Integer annee);
    
    @Query("select s.annee, count(s) from StagePfe s group by s.annee order by s.annee")
    List<Object[]> countStagesByAnnee();

    @Query("select f.id, f.intitule, count(s) from StagePfe s join s.etudiant e join e.filiere f group by f.id, f.intitule order by f.intitule")
    List<Object[]> countStagesByFiliere();
}
