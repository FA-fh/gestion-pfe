package com.jee.gestionpfe.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.jee.gestionpfe.entities.EncadrantAcademique;
import com.jee.gestionpfe.services.EncadrantAcademiqueService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/encadrants-academiques")
@RequiredArgsConstructor
@CrossOrigin("*")
public class EncadrantAcademiqueController {

    private final EncadrantAcademiqueService encadrantAcademiqueService;

    @PostMapping
    public EncadrantAcademique save(@RequestBody EncadrantAcademique encadrantAcademique) {
        return encadrantAcademiqueService.save(encadrantAcademique);
    }

    @GetMapping
    public List<EncadrantAcademique> findAll() {
        return encadrantAcademiqueService.findAll();
    }

    @GetMapping("/{id}")
    public EncadrantAcademique findById(@PathVariable Long id) {
        return encadrantAcademiqueService.findById(id);
    }

    @PutMapping("/{id}")
    public EncadrantAcademique update(@PathVariable Long id, @RequestBody EncadrantAcademique encadrantAcademique) {
        return encadrantAcademiqueService.update(id, encadrantAcademique);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        encadrantAcademiqueService.delete(id);
    }
}
