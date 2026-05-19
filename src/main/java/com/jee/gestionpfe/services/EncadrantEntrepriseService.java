package com.jee.gestionpfe.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jee.gestionpfe.entities.EncadrantEntreprise;
import com.jee.gestionpfe.entities.Entreprise;
import com.jee.gestionpfe.repositories.EncadrantEntrepriseRepository;
import com.jee.gestionpfe.repositories.EntrepriseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EncadrantEntrepriseService {

    private final EncadrantEntrepriseRepository encadrantEntrepriseRepository;
    private final EntrepriseRepository entrepriseRepository;

    public EncadrantEntreprise save(EncadrantEntreprise encadrantEntreprise) {
        if (encadrantEntreprise.getEntreprise() != null && encadrantEntreprise.getEntreprise().getId() != null) {
            Entreprise entreprise = entrepriseRepository.findById(encadrantEntreprise.getEntreprise().getId())
                    .orElseThrow(() -> new RuntimeException("Entreprise introuvable"));
            encadrantEntreprise.setEntreprise(entreprise);
        }
        return encadrantEntrepriseRepository.save(encadrantEntreprise);
    }

    public List<EncadrantEntreprise> findAll() {
        return encadrantEntrepriseRepository.findAll();
    }

    public EncadrantEntreprise findById(Long id) {
        return encadrantEntrepriseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Encadrant entreprise introuvable avec id : " + id));
    }

    public List<EncadrantEntreprise> findByEntreprise(Long entrepriseId) {
        return encadrantEntrepriseRepository.findByEntrepriseId(entrepriseId);
    }

    public EncadrantEntreprise update(Long id, EncadrantEntreprise encadrantEntreprise) {
        EncadrantEntreprise existingEncadrant = findById(id);

        existingEncadrant.setNom(encadrantEntreprise.getNom());
        existingEncadrant.setPrenom(encadrantEntreprise.getPrenom());
        existingEncadrant.setEmail(encadrantEntreprise.getEmail());
        existingEncadrant.setTelephone(encadrantEntreprise.getTelephone());

        if (encadrantEntreprise.getEntreprise() != null && encadrantEntreprise.getEntreprise().getId() != null) {
            Entreprise entreprise = entrepriseRepository.findById(encadrantEntreprise.getEntreprise().getId())
                    .orElseThrow(() -> new RuntimeException("Entreprise introuvable"));
            existingEncadrant.setEntreprise(entreprise);
        }

        return encadrantEntrepriseRepository.save(existingEncadrant);
    }

    public void delete(Long id) {
        encadrantEntrepriseRepository.deleteById(id);
    }
}
