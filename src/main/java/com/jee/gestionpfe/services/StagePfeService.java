package com.jee.gestionpfe.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jee.gestionpfe.entities.EncadrantAcademique;
import com.jee.gestionpfe.entities.EncadrantEntreprise;
import com.jee.gestionpfe.entities.Entreprise;
import com.jee.gestionpfe.entities.Etudiant;
import com.jee.gestionpfe.entities.StagePfe;
import com.jee.gestionpfe.repositories.EncadrantAcademiqueRepository;
import com.jee.gestionpfe.repositories.EncadrantEntrepriseRepository;
import com.jee.gestionpfe.repositories.EntrepriseRepository;
import com.jee.gestionpfe.repositories.EtudiantRepository;
import com.jee.gestionpfe.repositories.StagePfeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StagePfeService {

    private final StagePfeRepository stagePfeRepository;
    private final EtudiantRepository etudiantRepository;
    private final EntrepriseRepository entrepriseRepository;
    private final EncadrantEntrepriseRepository encadrantEntrepriseRepository;
    private final EncadrantAcademiqueRepository encadrantAcademiqueRepository;

    public StagePfe save(StagePfe stagePfe) {
        chargerRelations(stagePfe);
        return stagePfeRepository.save(stagePfe);
    }

    public List<StagePfe> findAll() {
        return stagePfeRepository.findAll();
    }

    public StagePfe findById(Long id) {
        return stagePfeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stage PFE introuvable avec id : " + id));
    }

    public List<StagePfe> findByAnnee(Integer annee) {
        return stagePfeRepository.findByAnnee(annee);
    }

    public List<StagePfe> findByFiliere(Long filiereId) {
        return stagePfeRepository.findByEtudiantFiliereId(filiereId);
    }

    public StagePfe update(Long id, StagePfe stagePfe) {
        StagePfe existingStage = findById(id);

        existingStage.setSujet(stagePfe.getSujet());
        existingStage.setDateDebut(stagePfe.getDateDebut());
        existingStage.setDateFin(stagePfe.getDateFin());
        existingStage.setDescription(stagePfe.getDescription());
        existingStage.setObjectifs(stagePfe.getObjectifs());
        existingStage.setSolution(stagePfe.getSolution());
        existingStage.setDemarche(stagePfe.getDemarche());
        existingStage.setOutils(stagePfe.getOutils());
        existingStage.setAnnee(stagePfe.getAnnee());

        existingStage.setEtudiant(stagePfe.getEtudiant());
        existingStage.setEntreprise(stagePfe.getEntreprise());
        existingStage.setEncadrantEntreprise(stagePfe.getEncadrantEntreprise());
        existingStage.setEncadrantAcademique(stagePfe.getEncadrantAcademique());

        chargerRelations(existingStage);

        return stagePfeRepository.save(existingStage);
    }

    public void delete(Long id) {
        stagePfeRepository.deleteById(id);
    }

    private void chargerRelations(StagePfe stagePfe) {
        if (stagePfe.getEtudiant() != null && stagePfe.getEtudiant().getId() != null) {
            Etudiant etudiant = etudiantRepository.findById(stagePfe.getEtudiant().getId())
                    .orElseThrow(() -> new RuntimeException("Etudiant introuvable"));
            stagePfe.setEtudiant(etudiant);
        }

        if (stagePfe.getEntreprise() != null && stagePfe.getEntreprise().getId() != null) {
            Entreprise entreprise = entrepriseRepository.findById(stagePfe.getEntreprise().getId())
                    .orElseThrow(() -> new RuntimeException("Entreprise introuvable"));
            stagePfe.setEntreprise(entreprise);
        }

        if (stagePfe.getEncadrantEntreprise() != null && stagePfe.getEncadrantEntreprise().getId() != null) {
            EncadrantEntreprise encadrantEntreprise = encadrantEntrepriseRepository
                    .findById(stagePfe.getEncadrantEntreprise().getId())
                    .orElseThrow(() -> new RuntimeException("Encadrant entreprise introuvable"));
            stagePfe.setEncadrantEntreprise(encadrantEntreprise);
        }

        if (stagePfe.getEncadrantAcademique() != null && stagePfe.getEncadrantAcademique().getId() != null) {
            EncadrantAcademique encadrantAcademique = encadrantAcademiqueRepository
                    .findById(stagePfe.getEncadrantAcademique().getId())
                    .orElseThrow(() -> new RuntimeException("Encadrant académique introuvable"));
            stagePfe.setEncadrantAcademique(encadrantAcademique);
        }
    }
    
    public List<StagePfe> search(Long filiereId, Integer annee) {
        if (filiereId != null && annee != null) {
            return stagePfeRepository.findByEtudiantFiliereIdAndAnnee(filiereId, annee);
        }

        if (filiereId != null) {
            return stagePfeRepository.findByEtudiantFiliereId(filiereId);
        }

        if (annee != null) {
            return stagePfeRepository.findByAnnee(annee);
        }

        return stagePfeRepository.findAll();
    }
}
