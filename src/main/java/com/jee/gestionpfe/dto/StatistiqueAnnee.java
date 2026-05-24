package com.jee.gestionpfe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatistiqueAnnee {

    private Integer annee;
    private Long total;
}