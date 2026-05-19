package com.jee.gestionpfe.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jee.gestionpfe.entities.Filiere;
import com.jee.gestionpfe.repositories.FiliereRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FiliereService {

    private final FiliereRepository filiereRepository;

    public Filiere save(Filiere filiere) {
        return filiereRepository.save(filiere);
    }

    public List<Filiere> findAll() {
        return filiereRepository.findAll();
    }

    public Filiere findById(Long id) {
        return filiereRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filiere introuvable avec id : " + id));
    }

    public Filiere update(Long id, Filiere filiere) {
        Filiere existingFiliere = findById(id);
        existingFiliere.setIntitule(filiere.getIntitule());
        return filiereRepository.save(existingFiliere);
    }

    public void delete(Long id) {
        filiereRepository.deleteById(id);
    }
}
