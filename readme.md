# SAE 302 - Groupe J5
📅 Début : 03/10/2022 - Fin : 04/12/2022

# 🚀 Lancer le projet :
### Depuis les sources :
1. `git clone https://gitlab.univ-lille.fr/sae302/2022/equipe-J5`
2. `cd equipe-J5`
3. `mvn clean javafx:run`  

### Depuis le [jar]() (Not working yet, SOON™) :
1. `wget https//hidoyat.fr/projects/equipe-J5.jar`
2. `java -jar equipe-J5.jar`

# 📖 Procédures
> Que faut-il faire pour ajouter à votre projet un nouveau problème de classification ?
1. Ajouter un CSV de référence pour ce nouveau type.
2. Ajouter une classe héritant de "AbstractData" ayant pour attributs chaque colonnes du CSV avec son bon type (double,
String, enum).
3. Ajouter ce type de données dans l'énumération DataType.

Optionnel:

4. En cas d'enum, mettre à jour la méthode setCategoryForData dans AbstractClassifier afin d'y expliciter son
comportement.
5. Ajouter une nouvelle condition dans la classe "AddPointsController" afin de pouvoir ajouter des points dynamiquement dans l'IHM.
6. Créer le "dynamic csv" dans les ressources ayant pour seul ligne l'en tête du fichier csv de référence.

# ❓ Problématique
La problématique professionnelle est de mettre en place l'organisation du projet et de créer, en équipe, une application en suivant une démarche de développement itérative et incrémentale. En partant d'un besoin décrit de manière imprécise ou incomplète par un client, l'objectif est de clarifier, compléter, collecter et formaliser le besoin, puis de développer une application communicante intégrant la manipulation des données et respectant les paradigmes de qualité (ergonomie des IHM, qualité logicielle…).

# ℹ️ Description
Le but de ce projet est de développer un outil de chargement, classification, affichage, et consultation d'un ensemble de données :

- Chargement : Les données seront fournies sous forme de fichiers CSV (Comma Separated Values) que le programme devra pouvoir charger pour construire le modèle ou pour les classifier ;
- Modélisation : Plusieurs jeux de données seront fournis (ex : des Iris, les passagers du Titanic). Chaque jeu de données devra être modélisé de façon appropriée dans le projet, mais les fonctionnalités demandées (dans la présente liste) devront être aussi indépendantes que possible de ces données. L'utilisation d'interfaces Java adéquates peut être un choix judicieux pour ce point ;
- Affichage : Une fois les données chargées, on doit pouvoir les visualiser sous forme de nuage de points en projetant sur deux attributs à choisir interactivement. Le type de ces attributs peut être numérique ou énuméré (un ensemble fini de valeurs discrètes). Dans le cas de valeurs énumérées, le programme choisira un ordre quelconque des valeurs ;
- Catégorisation : Le programme doit construire un modèle de classification des données à l'aide de l'algorithme kNN pour une valeur de k donnée. Après application de cet algorithme, chaque donnée sera associée à une classe. Une couleur sera associée automatiquement à chaque catégorie (jusqu'à 10 couleurs) pour la visualiser dans le nuage de points. On pourra relancer la catégorisation à tout moment, soit avec un nouveau k, soit avec des données additionnelles (saisie de points) ;
- Qualité : La robustesse des modèles devra être systématiquement calculée et accessible après apprentissage dans votre interface, en précisant la méthode d'évaluation ;
- Interaction : Sélectionner dans le nuage un point pour afficher la donnée qu’il représente (toutes les valeurs de ses attributs et sa catégorie). Optionnellement, le passage de la souris sur un point du nuage affichera en popup la donnée qu'il représente ;
- Saisie de point : Entrée manuelle de nouveaux points (avec toutes les valeurs des attributs). Le nouveau point saisi sera affiché dans le nuage de points automatiquement de façon différentiée pour le repérer facilement. Le point sera aussi classifié dans une des catégories déjà calculées (si c'est le cas).

# 🏹 Attendus
Cette SAÉ permet, après avoir collecté et formalisé les besoins d'un client, de développer une application de qualité répondant à ces besoins en mettant en place des outils de gestion de projet adaptés.

- Le projet sera réalisé en équipe de 4 personnes ;
- Le projet sera stocké sur le gitlab de l'université dans un dépôt privé ;
- Tous les membres d'une équipe doivent démontrer une participation active au développement (commits) ;
- Le projet donnera lieu à plusieurs rendus et à une présentation finale (mini-défense) d'une vingtaine de minutes devant deux professeurs des matières R3.02 et R3.04.

# 🔥 Technologies
<p align="center">
    <img alt="Java"       src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white"/>
    <img alt="Debian"     src="https://img.shields.io/badge/Debian-A81D33?style=for-the-badge&logo=debian&logoColor=white"/>
</p>

# 🔗 Links
- [Trello](https://trello.com/b/mfKQH5ar/sae302-groupe-j5)
- [Gitlab](https://gitlab.univ-lille.fr/sae302/2022/equipe-J5)
- [Moodle](https://moodle.univ-lille.fr/course/view.php?id=35684)

# 👥 Authors
- [Zaidi Mehdi](https://hidoyat.fr/)
- Lecointe Loïc
- Misplon Benoît
- Bouton Sacha
