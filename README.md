# Gestion PFE

Application backend JEE/Spring Boot pour la gestion des stages de Projet de Fin d'Etudes (PFE).  
Le projet expose une API REST permettant de gerer les filieres, les etudiants, les entreprises, les encadrants, les responsables de filiere et les stages PFE.

## Technologies utilisees

- Java 17
- Spring Boot 4.0.6
- Spring Web MVC
- Spring Data JPA
- MySQL
- Maven
- Lombok

## Structure du projet

```text
gestion-pfe/
├── src/
│   ├── main/
│   │   ├── java/com/jee/gestionpfe/
│   │   │   ├── controllers/      # Controleurs REST
│   │   │   ├── entities/         # Entites JPA
│   │   │   ├── repositories/     # Repositories Spring Data JPA
│   │   │   ├── services/         # Logique metier
│   │   │   └── GestionPfeApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/jee/gestionpfe/
├── pom.xml
├── mvnw
└── mvnw.cmd
```

## Fonctionnalites

- Gestion des filieres.
- Gestion des etudiants et rattachement a une filiere.
- Gestion des entreprises.
- Gestion des encadrants academiques.
- Gestion des encadrants d'entreprise et rattachement a une entreprise.
- Gestion des responsables de filiere.
- Gestion des stages PFE avec sujet, dates, description, objectifs, solution, demarche, outils et annee.
- Recherche des stages par annee.
- Recherche des stages ou etudiants par filiere.
- Recherche des encadrants d'entreprise par entreprise.

## Modele de donnees

### Filiere

- `id`
- `intitule`

### Etudiant

- `id`
- `cne`
- `nom`
- `prenom`
- `email`
- `telephone`
- `filiere`

### Entreprise

- `id`
- `nom`
- `adresse`
- `telephone`
- `email`
- `ville`
- `pays`
- `responsableEmail`

### EncadrantAcademique

- `id`
- `nom`
- `prenom`
- `email`
- `telephone`
- `departement`
- `etablissement`

### EncadrantEntreprise

- `id`
- `nom`
- `prenom`
- `email`
- `telephone`
- `entreprise`

### ResponsableFiliere

- `id`
- `nom`
- `prenom`
- `grade`
- `email`
- `telephone`
- `filiere`

### StagePfe

- `id`
- `sujet`
- `dateDebut`
- `dateFin`
- `description`
- `objectifs`
- `solution`
- `demarche`
- `outils`
- `annee`
- `etudiant`
- `entreprise`
- `encadrantEntreprise`
- `encadrantAcademique`

## Installation et lancement

### 1. Cloner le depot

```bash
git clone https://github.com/FA-fh/gestion-pfe.git
cd gestion-pfe
```

### 2. Verifier les prerequis

```bash
java -version
mvn -version
```

Le projet utilise Java 17.

### 3. Lancer l'application

Avec Maven installe :

```bash
mvn spring-boot:run
```

Ou avec le wrapper Maven du projet :

```bash
./mvnw spring-boot:run
```

Sous Windows :

```bash
mvnw.cmd spring-boot:run
```

L'application demarre sur :

```text
http://localhost:8080
