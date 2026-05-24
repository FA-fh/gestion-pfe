package com.jee.gestionpfe.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardStats {

    private long totalStages;
    private long totalEtudiants;
    private long totalEntreprises;
    private long totalFilieres;
    private long totalEncadrantsAcademiques;
    private long totalEncadrantsEntreprises;

    private List<StatistiqueAnnee> stagesParAnnee;
    private List<StatistiqueFiliere> stagesParFiliere;
}