# Buchverwaltung REST-API (Library Management System)

Ein modernes, skalierbares Backend-System zur Verwaltung von Bibliotheksressourcen. Dieses Projekt wurde entwickelt, um eine sichere, zustandslose Architektur unter Einhaltung von Clean Code Prinzipien zu demonstrieren.

##  Technologien (Tech Stack)
* **Backend-Framework:** Java 21, Spring Boot 3
* **Sicherheit:** Spring Security, zustandslose Authentifizierung via JWT (JSON Web Token)
* **Datenbank:** PostgreSQL, Spring Data JPA / Hibernate
* **Testing:** Automatisierte Unit- und Integrationstests mit JUnit 5 und Mockito (*Testautomatisierung*)
* **Dokumentation:** Interaktive API-Dokumentation mit OpenAPI (Swagger UI)
* **Build-Management:** Maven

##  Hauptfunktionen (Features)
* **Sichere Authentifizierung:** Registrierungs- und Login-Endpunkte mit JWT-Generierung und Validierung.
* **Rollenbasiertes Zugriffssystem:** Schutz spezifischer Routen durch Spring Security Filter-Chains (*Sicherheitsinfrastruktur*).
* **CRUD-Operationen:** Vollständige Verwaltung von Buch-Entitäten.
* **Echtzeit-Dokumentation:** Eingebaute Swagger-Oberfläche zum direkten Testen der Endpunkte im Browser.

##  Setup & Installation (Lokale Entwicklung)
1. **Repository klonen:**
   ```bash
   git clone [https://github.com/Amir-Rstm/Buchverwaltung.git](https://github.com/Amir-Rstm/Buchverwaltung.git)
2. Datenbank konfigurieren:
Die Zugangsdaten für PostgreSQL in der Datei src/main/resources/application.properties anpassen.

3. Projekt bauen und starten:

Bash
mvn clean install
mvn spring-boot:run
 API-Dokumentation testen
Nach dem erfolgreichen Start der Anwendung ist die Swagger UI unter folgendem Link erreichbar, um die REST-API direkt zu testen:
http://localhost:8080/swagger-ui/index.html

Autor: Amir Rostami - Student der Informatik (Universität Bayreuth)
