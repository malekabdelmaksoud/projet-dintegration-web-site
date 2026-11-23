# SGS — Système de Gestion et de Suivi des Stages

Application web de gestion des stages pour un établissement universitaire (ISET).

## Stack
- Backend: Java 17, Spring Boot 3, Spring MVC, Spring Data JPA, Validation
- Frontend: React + Vite
- Base de données: H2 en développement (facilement remplaçable par MySQL)
- Architecture: MVC en couches (Controller → Service → Repository → Entity)

## Démarrage
### Prérequis
Java 17+, Maven 3.9+, Node.js 20+ et npm.

### Backend
```powershell
cd backend
mvn spring-boot:run
```
API: http://localhost:8080/api

### Frontend
Dans un deuxième terminal:
```powershell
cd frontend
npm install
npm run dev
```
Interface: http://localhost:5173

## Fonctionnalités du MVP
- Consultation et gestion des entreprises
- Publication et consultation des offres
- Soumission d'une candidature par un étudiant
- Traitement administratif: accepter, refuser ou demander un complément
- Suivi du statut de la candidature
- Structure initiale pour les comptes et rôles

## Structure
```
backend/
  src/main/java/tn/iset/sgs/
    controller/  service/  repository/  entity/
frontend/
  src/
```

## Statuts
Demande: BROUILLON, EN_ATTENTE, A_COMPLETER, ACCEPTEE, REFUSEE.
Stage: PREVU, EN_COURS, RAPPORT_DEPOSE, CLOTURE.

> Les règles encore à confirmer dans le cahier des charges (formats/taille du rapport, comptes étudiants, cardinalité des encadrants) sont à valider avec l'établissement.
