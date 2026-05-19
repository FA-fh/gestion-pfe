package com.jee.gestionpfe.entities;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stage_pfe")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StagePfe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sujet;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String objectifs;

    @Column(columnDefinition = "TEXT")
    private String solution;

    @Column(columnDefinition = "TEXT")
    private String demarche;

    @Column(columnDefinition = "TEXT")
    private String outils;

    private Integer annee;

    @OneToOne
    @JoinColumn(name = "etudiant_id")
    private Etudiant etudiant;

    @ManyToOne
    @JoinColumn(name = "entreprise_id")
    private Entreprise entreprise;

    @ManyToOne
    @JoinColumn(name = "encadrant_entreprise_id")
    private EncadrantEntreprise encadrantEntreprise;

    @ManyToOne
    @JoinColumn(name = "encadrant_academique_id")
    private EncadrantAcademique encadrantAcademique;
}
