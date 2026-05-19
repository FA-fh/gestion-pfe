package com.jee.gestionpfe.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jee.gestionpfe.entities.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    List<Etudiant> findByFiliereId(Long filiereId);
}
