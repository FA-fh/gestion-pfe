package com.jee.gestionpfe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jee.gestionpfe.entities.Entreprise;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {
}
