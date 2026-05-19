package com.jee.gestionpfe.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponsableFiliere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String prenom;

    private String grade;

    private String email;

    private String telephone;

    @OneToOne
    @JoinColumn(name = "filiere_id")
    private Filiere filiere;
}