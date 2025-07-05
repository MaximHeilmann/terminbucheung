package com.terminbuchung.backend.booking;

import com.terminbuchung.backend.availability.AvailabilityService;
import com.terminbuchung.backend.customer.Customer;
import com.terminbuchung.backend.customer.CustomerRepository;
import com.terminbuchung.backend.service.ServiceType;
import com.terminbuchung.backend.service.ServiceTypeRepository;
import com.terminbuchung.backend.termin.Termin;
import com.terminbuchung.backend.termin.TerminRepository;
import com.terminbuchung.backend.termin.TerminStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class BookingService {
    
    private final TerminRepository terminRepository;
    private final CustomerRepository customerRepository;
    private final ServiceTypeRepository serviceTypeRepository;
    private final AvailabilityService availabilityService;
    
    public BookingService(TerminRepository terminRepository,
                         CustomerRepository customerRepository,
                         ServiceTypeRepository serviceTypeRepository,
                         AvailabilityService availabilityService) {
        this.terminRepository = terminRepository;
        this.customerRepository = customerRepository;
        this.serviceTypeRepository = serviceTypeRepository;
        this.availabilityService = availabilityService;
    }
    
    /**
     * Bucht einen Termin basierend auf BookingRequest
     */
    public BookingResult bookTermin(BookingRequest request) {
        // Validierung
        if (!request.isValid()) {
            return BookingResult.error("Ungültige Buchungsanfrage");
        }
        
        // Service-Type prüfen
        Optional<ServiceType> serviceType = serviceTypeRepository.findById(request.getServiceTypeId());
        if (serviceType.isEmpty()) {
            return BookingResult.error("Service nicht gefunden");
        }
        
        // Endzeit berechnen
        LocalDateTime endTime = request.getStartTime().plusMinutes(serviceType.get().getDurationMinutes());
        
        // Verfügbarkeit prüfen
        if (!availabilityService.isSlotAvailable(request.getStartTime(), endTime)) {
            return BookingResult.error("Termin ist nicht verfügbar");
        }
        
        // Kunde verwalten
        Customer customer = getOrCreateCustomer(request);
        if (customer == null) {
            return BookingResult.error("Kunde konnte nicht erstellt werden");
        }
        
        // Termin erstellen
        Termin termin = new Termin();
        termin.setServiceType(serviceType.get());
        termin.setCustomer(customer);
        termin.setStartTime(request.getStartTime());
        termin.setEndTime(endTime);
        termin.setNotes(request.getNotes());
        termin.setStatus(TerminStatus.GEBUCHT);
        
        // Speichern
        Termin savedTermin = terminRepository.save(termin);
        
        return BookingResult.success(savedTermin);
    }
    
    /**
     * Storniert einen Termin
     */
    public BookingResult cancelTermin(Long terminId) {
        Optional<Termin> termin = terminRepository.findById(terminId);
        if (termin.isEmpty()) {
            return BookingResult.error("Termin nicht gefunden");
        }
        
        Termin t = termin.get();
        t.setStatus(TerminStatus.STORNIERT);
        terminRepository.save(t);
        
        return BookingResult.success(t);
    }
    
    private Customer getOrCreateCustomer(BookingRequest request) {
        if (request.getCustomerId() != null) {
            return customerRepository.findById(request.getCustomerId()).orElse(null);
        }
        
        // Neuen Kunden erstellen
        if (request.isNewCustomer()) {
            Customer customer = new Customer();
            customer.setVorname(request.getCustomerVorname());
            customer.setNachname(request.getCustomerNachname());
            customer.setEmail(request.getCustomerEmail());
            return customerRepository.save(customer);
        }
        
        return null;
    }
} 