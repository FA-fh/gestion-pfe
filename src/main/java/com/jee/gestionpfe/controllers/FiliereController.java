package com.jee.gestionpfe.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.jee.gestionpfe.entities.Filiere;
import com.jee.gestionpfe.services.FiliereService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/filieres")
@RequiredArgsConstructor
@CrossOrigin("*")
public class FiliereController {

    private final FiliereService filiereService;

    @PostMapping
    public Filiere save(@RequestBody Filiere filiere) {
        return filiereService.save(filiere);
    }

    @GetMapping
    public List<Filiere> findAll() {
        return filiereService.findAll();
    }

    @GetMapping("/{id}")
    public Filiere findById(@PathVariable Long id) {
        return filiereService.findById(id);
    }

    @PutMapping("/{id}")
    public Filiere update(@PathVariable Long id, @RequestBody Filiere filiere) {
        return filiereService.update(id, filiere);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        filiereService.delete(id);
    }
}
