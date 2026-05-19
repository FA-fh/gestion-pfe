package com.jee.gestionpfe.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jee.gestionpfe.entities.Entreprise;
import com.jee.gestionpfe.repositories.EntrepriseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EntrepriseService {

    private final EntrepriseRepository entrepriseRepository;

    public Entreprise save(Entreprise entreprise) {
        return entrepriseRepository.save(entreprise);
    }

    public List<Entreprise> findAll() {
        return entrepriseRepository.findAll();
    }

    public Entreprise findById(Long id) {
        return entrepriseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entreprise introuvable avec id : " + id));
    }

    public Entreprise update(Long id, Entreprise entreprise) {
        Entreprise existingEntreprise = findById(id);

        existingEntreprise.setNom(entreprise.getNom());
        existingEntreprise.setAdresse(entreprise.getAdresse());
        existingEntreprise.setTelephone(entreprise.getTelephone());
        existingEntreprise.setEmail(entreprise.getEmail());
        existingEntreprise.setVille(entreprise.getVille());
        existingEntreprise.setPays(entreprise.getPays());
        existingEntreprise.setResponsableEmail(entreprise.getResponsableEmail());

        return entrepriseRepository.save(existingEntreprise);
    }

    public void delete(Long id) {
        entrepriseRepository.deleteById(id);
    }
}
