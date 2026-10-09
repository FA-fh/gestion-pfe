# Gestion PFE

Application full-stack pour la gestion des stages de Projet de Fin d'Etudes (PFE).

Le projet contient un backend Spring Boot qui expose une API REST et un frontend React.js qui consomme cette API.

## Technologies utilisées

### Backend

- Java 17
- Spring Boot
- Spring Web MVC
- Spring Data JPA
- Maven
- Lombok

### Frontend

- React.js
- JavaScript
- HTML
- CSS
- npm

### Base de données

- MySQL

## Fonctionnalités

- Gestion des filières.
- Gestion des étudiants et rattachement à une filière.
- Gestion des entreprises.
- Gestion des encadrants académiques.
- Gestion des encadrants d'entreprise.
- Gestion des responsables de filière.
- Gestion des stages PFE.
- Recherche des étudiants par filière.
- Recherche des stages par année.
- Recherche des stages par filière.
- Recherche des encadrants d'entreprise par entreprise.

## Structure du projet

```text
gestion-pfe/
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/jee/gestionpfe/
│   │   │   │   ├── controllers/
│   │   │   │   ├── entities/
│   │   │   │   ├── repositories/
│   │   │   │   ├── services/
│   │   │   │   └── GestionPfeApplication.java
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── test/
│   ├── pom.xml
│   ├── mvnw
│   └── mvnw.cmd
│
└── frontend/
    ├── src/
    ├── public/
    ├── package.json
    └── package-lock.json
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

## Configuration de la base de données

src/main/resources/application.properties

## Installation et lancement

### 1. Cloner le depot

```bash
git clone https://github.com/FA-fh/gestion-pfe.git
cd gestion-pfe
cd backend
```

### 2. Verifier les prerequis

```bash
java -version
mvn -version
```

Le projet utilise Java 17.

### 3. Lancer le backend Spring Boot

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
```

### 4. Lancer le frontend React.js

```bash
git clone https://github.com/FA-fh/gestion-pfe.git
cd gestion-pfe
cd frontend
```

```bash
npm install
npm start
npm run dev
```

Le frontend démarre généralement sur :

```text
http://localhost:3000
```

Ou 

```text
http://localhost:5173
```
