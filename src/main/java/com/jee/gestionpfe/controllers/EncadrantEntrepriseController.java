package com.jee.gestionpfe.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.jee.gestionpfe.entities.EncadrantEntreprise;
import com.jee.gestionpfe.services.EncadrantEntrepriseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/encadrants-entreprise")
@RequiredArgsConstructor
@CrossOrigin("*")
public class EncadrantEntrepriseController {

    private final EncadrantEntrepriseService encadrantEntrepriseService;

    @PostMapping
    public EncadrantEntreprise save(@RequestBody EncadrantEntreprise encadrantEntreprise) {
        return encadrantEntrepriseService.save(encadrantEntreprise);
    }

    @GetMapping
    public List<EncadrantEntreprise> findAll() {
        return encadrantEntrepriseService.findAll();
    }

    @GetMapping("/{id}")
    public EncadrantEntreprise findById(@PathVariable Long id) {
        return encadrantEntrepriseService.findById(id);
    }

    @GetMapping("/entreprise/{entrepriseId}")
    public List<EncadrantEntreprise> findByEntreprise(@PathVariable Long entrepriseId) {
        return encadrantEntrepriseService.findByEntreprise(entrepriseId);
    }

    @PutMapping("/{id}")
    public EncadrantEntreprise update(@PathVariable Long id, @RequestBody EncadrantEntreprise encadrantEntreprise) {
        return encadrantEntrepriseService.update(id, encadrantEntreprise);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        encadrantEntrepriseService.delete(id);
    }
}
