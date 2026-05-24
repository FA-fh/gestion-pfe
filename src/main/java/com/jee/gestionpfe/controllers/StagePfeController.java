package com.jee.gestionpfe.controllers;

import java.util.List;


import org.springframework.web.bind.annotation.*;

import com.jee.gestionpfe.entities.StagePfe;
import com.jee.gestionpfe.services.StagePfeService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.jee.gestionpfe.services.PdfReportService;

@RestController
@RequestMapping("/api/stages")
@RequiredArgsConstructor
@CrossOrigin("*")
public class StagePfeController {

    private final StagePfeService stagePfeService;
    private final PdfReportService pdfReportService;

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
    
    @GetMapping("/search")
    public List<StagePfe> search(
            @RequestParam(required = false) Long filiereId,
            @RequestParam(required = false) Integer annee) {
        return stagePfeService.search(filiereId, annee);
    }
    
    @GetMapping("/{id}/rapport")
    public ResponseEntity<byte[]> generateReport(@PathVariable Long id) {
        byte[] pdf = pdfReportService.generateStageReport(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=rapport-stage-" + id + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
