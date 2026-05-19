package com.jee.gestionpfe.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.jee.gestionpfe.entities.StagePfe;
import com.jee.gestionpfe.services.StagePfeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/stages")
@RequiredArgsConstructor
@CrossOrigin("*")
public class StagePfeController {

    private final StagePfeService stagePfeService;

    @PostMapping
    public StagePfe save(@RequestBody StagePfe stagePfe) {
        return stagePfeService.save(stagePfe);
    }

    @GetMapping
    public List<StagePfe> findAll() {
        return stagePfeService.findAll();
    }

    @GetMapping("/{id}")
    public StagePfe findById(@PathVariable Long id) {
        return stagePfeService.findById(id);
    }

    @GetMapping("/annee/{annee}")
    public List<StagePfe> findByAnnee(@PathVariable Integer annee) {
        return stagePfeService.findByAnnee(annee);
    }

    @GetMapping("/filiere/{filiereId}")
    public List<StagePfe> findByFiliere(@PathVariable Long filiereId) {
        return stagePfeService.findByFiliere(filiereId);
    }

    @PutMapping("/{id}")
    public StagePfe update(@PathVariable Long id, @RequestBody StagePfe stagePfe) {
        return stagePfeService.update(id, stagePfe);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        stagePfeService.delete(id);
    }
}
