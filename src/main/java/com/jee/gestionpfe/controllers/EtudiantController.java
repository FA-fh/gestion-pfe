package com.jee.gestionpfe.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.jee.gestionpfe.entities.Etudiant;
import com.jee.gestionpfe.services.EtudiantService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/etudiants")
@RequiredArgsConstructor
@CrossOrigin("*")
public class EtudiantController {

    private final EtudiantService etudiantService;

    @PostMapping
    public Etudiant save(@RequestBody Etudiant etudiant) {
        return etudiantService.save(etudiant);
    }

    @GetMapping
    public List<Etudiant> findAll() {
        return etudiantService.findAll();
    }

    @GetMapping("/{id}")
    public Etudiant findById(@PathVariable Long id) {
        return etudiantService.findById(id);
    }

    @GetMapping("/filiere/{filiereId}")
    public List<Etudiant> findByFiliere(@PathVariable Long filiereId) {
        return etudiantService.findByFiliere(filiereId);
    }

    @PutMapping("/{id}")
    public Etudiant update(@PathVariable Long id, @RequestBody Etudiant etudiant) {
        return etudiantService.update(id, etudiant);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        etudiantService.delete(id);
    }
}
