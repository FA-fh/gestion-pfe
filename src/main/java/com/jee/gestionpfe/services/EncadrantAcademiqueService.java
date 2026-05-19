package com.jee.gestionpfe.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jee.gestionpfe.entities.EncadrantAcademique;
import com.jee.gestionpfe.repositories.EncadrantAcademiqueRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EncadrantAcademiqueService {

    private final EncadrantAcademiqueRepository encadrantAcademiqueRepository;

    public EncadrantAcademique save(EncadrantAcademique encadrantAcademique) {
        return encadrantAcademiqueRepository.save(encadrantAcademique);
    }

    public List<EncadrantAcademique> findAll() {
        return encadrantAcademiqueRepository.findAll();
    }

    public EncadrantAcademique findById(Long id) {
        return encadrantAcademiqueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Encadrant académique introuvable avec id : " + id));
    }

    public EncadrantAcademique update(Long id, EncadrantAcademique encadrantAcademique) {
        EncadrantAcademique existingEncadrant = findById(id);

        existingEncadrant.setNom(encadrantAcademique.getNom());
        existingEncadrant.setPrenom(encadrantAcademique.getPrenom());
        existingEncadrant.setEmail(encadrantAcademique.getEmail());
        existingEncadrant.setTelephone(encadrantAcademique.getTelephone());
        existingEncadrant.setDepartement(encadrantAcademique.getDepartement());
        existingEncadrant.setEtablissement(encadrantAcademique.getEtablissement());

        return encadrantAcademiqueRepository.save(existingEncadrant);
    }

    public void delete(Long id) {
        encadrantAcademiqueRepository.deleteById(id);
    }
}
