package com.jee.gestionpfe.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.jee.gestionpfe.entities.Entreprise;
import com.jee.gestionpfe.services.EntrepriseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/entreprises")
@RequiredArgsConstructor
@CrossOrigin("*")
public class EntrepriseController {

    private final EntrepriseService entrepriseService;

    @PostMapping
    public Entreprise save(@RequestBody Entreprise entreprise) {
        return entrepriseService.save(entreprise);
    }

    @GetMapping
    public List<Entreprise> findAll() {
        return entrepriseService.findAll();
    }

    @GetMapping("/{id}")
    public Entreprise findById(@PathVariable Long id) {
        return entrepriseService.findById(id);
    }

    @PutMapping("/{id}")
    public Entreprise update(@PathVariable Long id, @RequestBody Entreprise entreprise) {
        return entrepriseService.update(id, entreprise);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        entrepriseService.delete(id);
    }
}
