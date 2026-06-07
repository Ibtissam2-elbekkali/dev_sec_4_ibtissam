# TP 4 : Stockage Sécurisé de Données sur Android

Ce projet implémente différentes méthodes de stockage sécurisé sur Android, notamment le stockage interne, externe (Scoped Storage), Room avec SQLCipher, et Jetpack Security.

## Fonctionnalités
- Stockage interne privé.
- Stockage externe avec gestion des permissions (API < 29) et Scoped Storage.
- Base de données Room chiffrée avec AES-256 via SQLCipher.
- Chiffrement de fichiers avec Jetpack Security Crypto.
- Authentification sécurisée (Hachage PBKDF2).
- Journalisation chiffrée des actions utilisateur.

## Architecture
Le projet est structuré en couches :
- **activities** : Gestion de l'interface utilisateur et de la navigation.
- **adapters** : Adaptateurs pour RecyclerView et ViewPager2.
- **database** : Entités, DAOs et gestionnaire de base de données chiffrée.
- **fragments** : Composants UI pour chaque mode de stockage.
- **security** : Utilitaires de chiffrement, hachage et gestion du Keystore.
- **storage** : Gestionnaires pour les fichiers système et MediaStore.

## Réalisation
Réalisé par : Ibtissam

## Démo Vidéo


https://github.com/user-attachments/assets/1a6d3018-6ff6-4b45-bbb4-5a37cdbfe83c


