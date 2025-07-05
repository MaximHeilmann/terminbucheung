package com.terminbuchung.backend.availability;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/availability")
@CrossOrigin(origins = "http://localhost:3000")
public class AvailabilityController {
    
    private final AvailabilityService availabilityService;
    
    public AvailabilityController(AvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }
    
    /**
     * Verfügbare Termine für einen bestimmten Tag
     * GET /api/availability/2025-01-20?serviceId=1
     */
    @GetMapping("/{date}")
    public List<TimeSlot> getAvailableSlots(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam Long serviceId) {
        return availabilityService.getAvailableSlots(date, serviceId);
    }
    
    /**
     * Verfügbare Termine für eine ganze Woche
     * GET /api/availability/week/2025-01-20?serviceId=1
     */
    @GetMapping("/week/{startDate}")
    public List<DayAvailability> getWeekAvailability(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam Long serviceId) {
        return availabilityService.getWeekAvailability(startDate, serviceId);
    }
    
    /**
     * Prüft ob ein bestimmter Zeitslot verfügbar ist
     * GET /api/availability/check?start=2025-01-20T10:00&end=2025-01-20T11:00
     */
    @GetMapping("/check")
    public boolean checkSlotAvailability(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) java.time.LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) java.time.LocalDateTime end) {
        return availabilityService.isSlotAvailable(start, end);
    }
} 