package com.terminbuchung.backend.availability;

import com.terminbuchung.backend.service.ServiceType;
import com.terminbuchung.backend.service.ServiceTypeRepository;
import com.terminbuchung.backend.termin.TerminRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AvailabilityService {
    
    private final BusinessHoursRepository businessHoursRepository;
    private final TerminRepository terminRepository;
    private final ServiceTypeRepository serviceTypeRepository;
    
    public AvailabilityService(BusinessHoursRepository businessHoursRepository,
                              TerminRepository terminRepository,
                              ServiceTypeRepository serviceTypeRepository) {
        this.businessHoursRepository = businessHoursRepository;
        this.terminRepository = terminRepository;
        this.serviceTypeRepository = serviceTypeRepository;
    }
    
    /**
     * Berechnet verfügbare Termine für einen bestimmten Tag und Service
     */
    public List<TimeSlot> getAvailableSlots(LocalDate date, Long serviceTypeId) {
        Optional<ServiceType> serviceType = serviceTypeRepository.findById(serviceTypeId);
        if (serviceType.isEmpty()) {
            return new ArrayList<>();
        }
        
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        Optional<BusinessHours> businessHours = businessHoursRepository.findByDayOfWeekAndActiveTrue(dayOfWeek);
        
        if (businessHours.isEmpty()) {
            return new ArrayList<>(); // Geschlossen
        }
        
        return calculateAvailableSlots(date, businessHours.get(), serviceType.get());
    }
    
    /**
     * Berechnet verfügbare Termine für eine Woche
     */
    public List<DayAvailability> getWeekAvailability(LocalDate startDate, Long serviceTypeId) {
        List<DayAvailability> weekAvailability = new ArrayList<>();
        
        for (int i = 0; i < 7; i++) {
            LocalDate date = startDate.plusDays(i);
            List<TimeSlot> slots = getAvailableSlots(date, serviceTypeId);
            weekAvailability.add(new DayAvailability(date, slots));
        }
        
        return weekAvailability;
    }
    
    /**
     * Prüft ob ein Termin verfügbar ist
     */
    public boolean isSlotAvailable(LocalDateTime startTime, LocalDateTime endTime) {
        return !terminRepository.hasConflictingTermin(startTime, endTime);
    }
    
    private List<TimeSlot> calculateAvailableSlots(LocalDate date, BusinessHours businessHours, ServiceType serviceType) {
        List<TimeSlot> availableSlots = new ArrayList<>();
        
        LocalTime currentTime = businessHours.getOpenTime();
        LocalTime closeTime = businessHours.getCloseTime();
        int serviceDuration = serviceType.getDurationMinutes();
        
        while (currentTime.plusMinutes(serviceDuration).isBefore(closeTime) || 
               currentTime.plusMinutes(serviceDuration).equals(closeTime)) {
            
            LocalDateTime slotStart = LocalDateTime.of(date, currentTime);
            LocalDateTime slotEnd = slotStart.plusMinutes(serviceDuration);
            
            // Prüfen ob Slot verfügbar ist
            if (isSlotAvailable(slotStart, slotEnd)) {
                availableSlots.add(new TimeSlot(slotStart, slotEnd, serviceType));
            }
            
            // Nächster Slot (15 Minuten Intervall)
            currentTime = currentTime.plusMinutes(15);
        }
        
        return availableSlots;
    }
} 