package com.jee.gestionpfe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatistiqueFiliere {

    private Long filiereId;
    private String filiere;
    private Long total;
}