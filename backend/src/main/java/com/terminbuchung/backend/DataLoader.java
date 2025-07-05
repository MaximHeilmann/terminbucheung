package com.terminbuchung.backend;

import com.terminbuchung.backend.availability.BusinessHours;
import com.terminbuchung.backend.availability.BusinessHoursRepository;
import com.terminbuchung.backend.service.ServiceType;
import com.terminbuchung.backend.service.ServiceTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Component
public class DataLoader implements CommandLineRunner {

    private final ServiceTypeRepository serviceTypeRepository;
    private final BusinessHoursRepository businessHoursRepository;

    public DataLoader(ServiceTypeRepository serviceTypeRepository, 
                     BusinessHoursRepository businessHoursRepository) {
        this.serviceTypeRepository = serviceTypeRepository;
        this.businessHoursRepository = businessHoursRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Nur laden wenn noch keine Daten vorhanden
        if (serviceTypeRepository.count() == 0) {
            loadServiceTypes();
        }
        
        if (businessHoursRepository.count() == 0) {
            loadBusinessHours();
        }
    }

    private void loadServiceTypes() {
        // Friseur Services
        ServiceType haarschnitt = new ServiceType();
        haarschnitt.setName("Haarschnitt");
        haarschnitt.setCategory("Friseur");
        haarschnitt.setDurationMinutes(30);
        haarschnitt.setPrice(25.00);
        haarschnitt.setDescription("Professioneller Haarschnitt mit Styling");
        haarschnitt.setActive(true);
        serviceTypeRepository.save(haarschnitt);

        ServiceType haarfarbe = new ServiceType();
        haarfarbe.setName("Haarfarbe");
        haarfarbe.setCategory("Friseur");
        haarfarbe.setDurationMinutes(90);
        haarfarbe.setPrice(65.00);
        haarfarbe.setDescription("Komplette Haarfärbung mit Beratung");
        haarfarbe.setActive(true);
        serviceTypeRepository.save(haarfarbe);

        ServiceType dauerwelle = new ServiceType();
        dauerwelle.setName("Dauerwelle");
        dauerwelle.setCategory("Friseur");
        dauerwelle.setDurationMinutes(120);
        dauerwelle.setPrice(85.00);
        dauerwelle.setDescription("Professionelle Dauerwelle");
        dauerwelle.setActive(true);
        serviceTypeRepository.save(dauerwelle);

        // Arzt Services
        ServiceType allgemeinuntersuchung = new ServiceType();
        allgemeinuntersuchung.setName("Allgemeinuntersuchung");
        allgemeinuntersuchung.setCategory("Arzt");
        allgemeinuntersuchung.setDurationMinutes(30);
        allgemeinuntersuchung.setPrice(45.00);
        allgemeinuntersuchung.setDescription("Umfassende Gesundheitsuntersuchung");
        allgemeinuntersuchung.setActive(true);
        serviceTypeRepository.save(allgemeinuntersuchung);

        ServiceType blutabnahme = new ServiceType();
        blutabnahme.setName("Blutabnahme");
        blutabnahme.setCategory("Arzt");
        blutabnahme.setDurationMinutes(15);
        blutabnahme.setPrice(20.00);
        blutabnahme.setDescription("Blutentnahme für Laboruntersuchungen");
        blutabnahme.setActive(true);
        serviceTypeRepository.save(blutabnahme);

        ServiceType impfung = new ServiceType();
        impfung.setName("Impfung");
        impfung.setCategory("Arzt");
        impfung.setDurationMinutes(20);
        impfung.setPrice(35.00);
        impfung.setDescription("Schutzimpfungen aller Art");
        impfung.setActive(true);
        serviceTypeRepository.save(impfung);

        // Beratung Services
        ServiceType finanzberatung = new ServiceType();
        finanzberatung.setName("Finanzberatung");
        finanzberatung.setCategory("Beratung");
        finanzberatung.setDurationMinutes(60);
        finanzberatung.setPrice(120.00);
        finanzberatung.setDescription("Umfassende Finanz- und Anlageberatung");
        finanzberatung.setActive(true);
        serviceTypeRepository.save(finanzberatung);

        ServiceType rechtsberatung = new ServiceType();
        rechtsberatung.setName("Rechtsberatung");
        rechtsberatung.setCategory("Beratung");
        rechtsberatung.setDurationMinutes(45);
        rechtsberatung.setPrice(150.00);
        rechtsberatung.setDescription("Juristische Beratung in allen Rechtsfragen");
        rechtsberatung.setActive(true);
        serviceTypeRepository.save(rechtsberatung);

        ServiceType steuerberatung = new ServiceType();
        steuerberatung.setName("Steuerberatung");
        steuerberatung.setCategory("Beratung");
        steuerberatung.setDurationMinutes(60);
        steuerberatung.setPrice(100.00);
        steuerberatung.setDescription("Professionelle Steuerberatung und -planung");
        steuerberatung.setActive(true);
        serviceTypeRepository.save(steuerberatung);

        System.out.println("✅ Service-Types geladen: " + serviceTypeRepository.count() + " Services");
    }

    private void loadBusinessHours() {
        // Montag bis Freitag: 9:00 - 18:00
        for (DayOfWeek day : new DayOfWeek[]{DayOfWeek.MONDAY, DayOfWeek.TUESDAY, 
                                           DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY}) {
            BusinessHours hours = new BusinessHours();
            hours.setDayOfWeek(day);
            hours.setOpenTime(LocalTime.of(9, 0));
            hours.setCloseTime(LocalTime.of(18, 0));
            hours.setActive(true);
            businessHoursRepository.save(hours);
        }

        // Samstag: 9:00 - 16:00
        BusinessHours saturday = new BusinessHours();
        saturday.setDayOfWeek(DayOfWeek.SATURDAY);
        saturday.setOpenTime(LocalTime.of(9, 0));
        saturday.setCloseTime(LocalTime.of(16, 0));
        saturday.setActive(true);
        businessHoursRepository.save(saturday);

        // Sonntag: Geschlossen
        BusinessHours sunday = new BusinessHours();
        sunday.setDayOfWeek(DayOfWeek.SUNDAY);
        sunday.setOpenTime(LocalTime.of(10, 0));
        sunday.setCloseTime(LocalTime.of(14, 0));
        sunday.setActive(false); // Geschlossen
        businessHoursRepository.save(sunday);

        System.out.println("✅ Geschäftszeiten geladen: Mo-Fr 9-18, Sa 9-16, So geschlossen");
    }
} 