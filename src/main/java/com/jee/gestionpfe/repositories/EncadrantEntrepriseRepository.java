package com.jee.gestionpfe.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jee.gestionpfe.entities.EncadrantEntreprise;

public interface EncadrantEntrepriseRepository extends JpaRepository<EncadrantEntreprise, Long> {
    List<EncadrantEntreprise> findByEntrepriseId(Long entrepriseId);
}
