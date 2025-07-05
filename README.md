# 🏢 Universal Terminbuchung

**Moderne, vollständig funktionsfähige Terminbuchungs-Plattform**  
für Friseure, Arztpraxen, Beratungsunternehmen und alle anderen Dienstleister.

## ✨ Features

### 🎯 Für Kunden
- **Intuitive Service-Auswahl** nach Kategorien (Friseur, Arzt, Beratung)
- **Intelligenter Kalender** mit Echtzeit-Verfügbarkeit
- **Smart Zeitslot-Auswahl** (Vormittag, Nachmittag, Abend)
- **5-Schritt Buchungsprozess** mit Validierung
- **Sofortige Terminbestätigung**

### 🔧 Für Anbieter
- **Flexible Service-Verwaltung** (Dauer, Preise, Kategorien)
- **Automatische Verfügbarkeitsberechnung**
- **Geschäftszeiten-Management**
- **Terminübersicht und -verwaltung**
- **Stornierungsfunktion**

### 🚀 Technische Highlights
- **Enterprise-Level Backend-Architektur**
- **Package-by-Feature Struktur**
- **Intelligente Konflikterkennung**
- **REST-API mit DTOs**
- **Modernes React-Frontend**
- **Material-UI Design System**

## 🛠 Technologien

### Backend
- **Java 17** + **Spring Boot 3.2.1**
- **Spring Data JPA** für Datenbankzugriff
- **PostgreSQL** Datenbank
- **Maven** Build-Management
- **REST-API** mit JSON

### Frontend
- **React 18** mit modernen Hooks
- **Material-UI (MUI)** Design System
- **Axios** für API-Kommunikation
- **Day.js** für Datum/Zeit-Handling
- **React Router** für Navigation

### Architektur
- **Package-by-Feature** Backend-Struktur
- **Service-Repository-Controller** Pattern
- **DTO-basierte API**
- **Responsive Design**
- **Component-based Frontend**

## 🚀 Schnellstart

### Voraussetzungen
- Java 17+
- Node.js 16+
- PostgreSQL
- Maven

### Backend starten
```bash
cd backend
./mvnw spring-boot:run
```
Backend läuft auf: `http://localhost:8080`

### Frontend starten
```bash
cd terminfrontend
npm install
npm start
```
Frontend läuft auf: `http://localhost:3000`

### Datenbank einrichten
```sql
-- PostgreSQL Datenbank erstellen
CREATE DATABASE terminbuchung;
```

Konfiguration in `backend/src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/terminbuchung
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=create-drop
```

## 📁 Projektstruktur

```
terminbuchung/
├── backend/                    # Spring Boot Backend
│   ├── src/main/java/com/terminbuchung/backend/
│   │   ├── customer/          # Kunden-Management
│   │   ├── termin/            # Termin-Verwaltung
│   │   ├── service/           # Service-Types
│   │   ├── availability/      # Verfügbarkeits-Logic
│   │   └── booking/           # Buchungs-Workflow
│   └── pom.xml
├── terminfrontend/            # React Frontend
│   ├── src/
│   │   ├── components/        # Wiederverwendbare Komponenten
│   │   ├── pages/            # Haupt-Seiten
│   │   └── services/         # API-Services
│   └── package.json
└── README.md
```

## 🎨 Screenshots

### Homepage - Service-Übersicht
Moderne Kategorien-basierte Service-Auswahl mit Preisen und Dauern.

### Buchungsprozess
5-Schritt Workflow mit intelligentem Kalender und Zeitslot-Auswahl.

### Terminverwaltung
Übersichtliche Darstellung aller Termine mit Such- und Stornierfunktion.

## 🔗 API-Endpunkte

### Services
- `GET /api/services` - Alle verfügbaren Services
- `GET /api/services/categories` - Service-Kategorien
- `GET /api/services/category/{category}` - Services nach Kategorie

### Verfügbarkeit
- `GET /api/availability/{date}?serviceId={id}` - Verfügbare Termine
- `GET /api/availability/week/{startDate}?serviceId={id}` - Wochenverfügbarkeit

### Buchung
- `POST /api/booking` - Termin buchen
- `DELETE /api/booking/{id}` - Termin stornieren

### Termine
- `GET /api/termine` - Alle Termine
- `GET /api/customers` - Alle Kunden

## 🏗 Deployment

### Mit Docker (optional)
```bash
# Backend
cd backend
docker build -t terminbuchung-backend .
docker run -p 8080:8080 terminbuchung-backend

# Frontend
cd terminfrontend
docker build -t terminbuchung-frontend .
docker run -p 3000:3000 terminbuchung-frontend
```

### Cloud-Deployment
- **Backend**: Heroku, Railway, AWS
- **Frontend**: Vercel, Netlify
- **Datenbank**: PostgreSQL auf Heroku, Supabase

## 🤝 Mitwirken

1. Repository forken
2. Feature-Branch erstellen (`git checkout -b feature/AmazingFeature`)
3. Änderungen committen (`git commit -m 'Add AmazingFeature'`)
4. Branch pushen (`git push origin feature/AmazingFeature`)
5. Pull Request erstellen

## 📝 Lizenz

Dieses Projekt steht unter der MIT-Lizenz. Siehe `LICENSE` Datei für Details.

## 👨‍💻 Entwickelt von

**[Ihr Name]**
- 💼 [LinkedIn](https://linkedin.com/in/ihr-profile)
- 🐙 [GitHub](https://github.com/ihr-username)
- 📧 [E-Mail](mailto:ihre-email@example.com)

---

⭐ **Gefällt Ihnen das Projekt? Geben Sie ihm einen Stern!** ⭐ 