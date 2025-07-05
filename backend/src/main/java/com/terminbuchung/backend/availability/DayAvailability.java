package com.terminbuchung.backend.availability;

import java.time.LocalDate;
import java.util.List;

public class DayAvailability {
    private LocalDate date;
    private List<TimeSlot> availableSlots;
    private boolean isOpen;
    
    public DayAvailability() {}
    
    public DayAvailability(LocalDate date, List<TimeSlot> availableSlots) {
        this.date = date;
        this.availableSlots = availableSlots;
        this.isOpen = !availableSlots.isEmpty();
    }
    
    // Getter und Setter
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    
    public List<TimeSlot> getAvailableSlots() { return availableSlots; }
    public void setAvailableSlots(List<TimeSlot> availableSlots) { 
        this.availableSlots = availableSlots;
        this.isOpen = !availableSlots.isEmpty();
    }
    
    public boolean isOpen() { return isOpen; }
    public void setOpen(boolean open) { this.isOpen = open; }
    
    public int getAvailableSlotCount() {
        return availableSlots != null ? availableSlots.size() : 0;
    }
} 