package com.jee.gestionpfe.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jee.gestionpfe.entities.Filiere;
import com.jee.gestionpfe.entities.ResponsableFiliere;
import com.jee.gestionpfe.repositories.FiliereRepository;
import com.jee.gestionpfe.repositories.ResponsableFiliereRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResponsableFiliereService {

    private final ResponsableFiliereRepository responsableFiliereRepository;
    private final FiliereRepository filiereRepository;

    public ResponsableFiliere save(ResponsableFiliere responsableFiliere) {
        if (responsableFiliere.getFiliere() != null && responsableFiliere.getFiliere().getId() != null) {
            Filiere filiere = filiereRepository.findById(responsableFiliere.getFiliere().getId())
                    .orElseThrow(() -> new RuntimeException("Filiere introuvable"));
            responsableFiliere.setFiliere(filiere);
        }
        return responsableFiliereRepository.save(responsableFiliere);
    }

    public List<ResponsableFiliere> findAll() {
        return responsableFiliereRepository.findAll();
    }

    public ResponsableFiliere findById(Long id) {
        return responsableFiliereRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Responsable filière introuvable avec id : " + id));
    }

    public ResponsableFiliere update(Long id, ResponsableFiliere responsableFiliere) {
        ResponsableFiliere existingResponsable = findById(id);

        existingResponsable.setNom(responsableFiliere.getNom());
        existingResponsable.setPrenom(responsableFiliere.getPrenom());
        existingResponsable.setGrade(responsableFiliere.getGrade());
        existingResponsable.setEmail(responsableFiliere.getEmail());
        existingResponsable.setTelephone(responsableFiliere.getTelephone());

        if (responsableFiliere.getFiliere() != null && responsableFiliere.getFiliere().getId() != null) {
            Filiere filiere = filiereRepository.findById(responsableFiliere.getFiliere().getId())
                    .orElseThrow(() -> new RuntimeException("Filiere introuvable"));
            existingResponsable.setFiliere(filiere);
        }

        return responsableFiliereRepository.save(existingResponsable);
    }

    public void delete(Long id) {
        responsableFiliereRepository.deleteById(id);
    }
}
