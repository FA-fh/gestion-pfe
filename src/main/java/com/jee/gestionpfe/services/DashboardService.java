package com.jee.gestionpfe.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jee.gestionpfe.dto.DashboardStats;
import com.jee.gestionpfe.dto.StatistiqueAnnee;
import com.jee.gestionpfe.dto.StatistiqueFiliere;
import com.jee.gestionpfe.repositories.EncadrantAcademiqueRepository;
import com.jee.gestionpfe.repositories.EncadrantEntrepriseRepository;
import com.jee.gestionpfe.repositories.EntrepriseRepository;
import com.jee.gestionpfe.repositories.EtudiantRepository;
import com.jee.gestionpfe.repositories.FiliereRepository;
import com.jee.gestionpfe.repositories.StagePfeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final StagePfeRepository stagePfeRepository;
    private final EtudiantRepository etudiantRepository;
    private final EntrepriseRepository entrepriseRepository;
    private final FiliereRepository filiereRepository;
    private final EncadrantAcademiqueRepository encadrantAcademiqueRepository;
    private final EncadrantEntrepriseRepository encadrantEntrepriseRepository;

    public DashboardStats getStats() {
        List<StatistiqueAnnee> stagesParAnnee = stagePfeRepository.countStagesByAnnee()
                .stream()
                .map(row -> new StatistiqueAnnee((Integer) row[0], (Long) row[1]))
                .toList();

        List<StatistiqueFiliere> stagesParFiliere = stagePfeRepository.countStagesByFiliere()
                .stream()
                .map(row -> new StatistiqueFiliere((Long) row[0], (String) row[1], (Long) row[2]))
                .toList();

        return DashboardStats.builder()
                .totalStages(stagePfeRepository.count())
                .totalEtudiants(etudiantRepository.count())
                .totalEntreprises(entrepriseRepository.count())
                .totalFilieres(filiereRepository.count())
                .totalEncadrantsAcademiques(encadrantAcademiqueRepository.count())
                .totalEncadrantsEntreprises(encadrantEntrepriseRepository.count())
                .stagesParAnnee(stagesParAnnee)
                .stagesParFiliere(stagesParFiliere)
                .build();
    }
}