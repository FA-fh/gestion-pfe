package com.jee.gestionpfe.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cne;

    private String nom;

    private String prenom;

    private String email;

    private String telephone;

    @ManyToOne
    @JoinColumn(name = "filiere_id")
    private Filiere filiere;
}