package com.jee.gestionpfe.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.jee.gestionpfe.entities.ResponsableFiliere;
import com.jee.gestionpfe.services.ResponsableFiliereService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/responsables-filieres")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ResponsableFiliereController {

    private final ResponsableFiliereService responsableFiliereService;

    @PostMapping
    public ResponsableFiliere save(@RequestBody ResponsableFiliere responsableFiliere) {
        return responsableFiliereService.save(responsableFiliere);
    }

    @GetMapping
    public List<ResponsableFiliere> findAll() {
        return responsableFiliereService.findAll();
    }

    @GetMapping("/{id}")
    public ResponsableFiliere findById(@PathVariable Long id) {
        return responsableFiliereService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponsableFiliere update(@PathVariable Long id, @RequestBody ResponsableFiliere responsableFiliere) {
        return responsableFiliereService.update(id, responsableFiliere);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        responsableFiliereService.delete(id);
    }
}
