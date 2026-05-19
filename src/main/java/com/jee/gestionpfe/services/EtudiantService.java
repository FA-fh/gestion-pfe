package com.jee.gestionpfe.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jee.gestionpfe.entities.Etudiant;
import com.jee.gestionpfe.entities.Filiere;
import com.jee.gestionpfe.repositories.EtudiantRepository;
import com.jee.gestionpfe.repositories.FiliereRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EtudiantService {

    private final EtudiantRepository etudiantRepository;
    private final FiliereRepository filiereRepository;

    public Etudiant save(Etudiant etudiant) {
        if (etudiant.getFiliere() != null && etudiant.getFiliere().getId() != null) {
            Filiere filiere = filiereRepository.findById(etudiant.getFiliere().getId())
                    .orElseThrow(() -> new RuntimeException("Filiere introuvable"));
            etudiant.setFiliere(filiere);
        }
        return etudiantRepository.save(etudiant);
    }

    public List<Etudiant> findAll() {
        return etudiantRepository.findAll();
    }

    public Etudiant findById(Long id) {
        return etudiantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Etudiant introuvable avec id : " + id));
    }

    public List<Etudiant> findByFiliere(Long filiereId) {
        return etudiantRepository.findByFiliereId(filiereId);
    }

    public Etudiant update(Long id, Etudiant etudiant) {
        Etudiant existingEtudiant = findById(id);

        existingEtudiant.setCne(etudiant.getCne());
        existingEtudiant.setNom(etudiant.getNom());
        existingEtudiant.setPrenom(etudiant.getPrenom());
        existingEtudiant.setEmail(etudiant.getEmail());
        existingEtudiant.setTelephone(etudiant.getTelephone());

        if (etudiant.getFiliere() != null && etudiant.getFiliere().getId() != null) {
            Filiere filiere = filiereRepository.findById(etudiant.getFiliere().getId())
                    .orElseThrow(() -> new RuntimeException("Filiere introuvable"));
            existingEtudiant.setFiliere(filiere);
        }

        return etudiantRepository.save(existingEtudiant);
    }

    public void delete(Long id) {
        etudiantRepository.deleteById(id);
    }
}
