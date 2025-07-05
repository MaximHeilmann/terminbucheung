package com.terminbuchung.backend.availability;

import com.terminbuchung.backend.service.ServiceType;
import java.time.LocalDateTime;

public class TimeSlot {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private ServiceType serviceType;
    private boolean available;
    
    public TimeSlot() {}
    
    public TimeSlot(LocalDateTime startTime, LocalDateTime endTime, ServiceType serviceType) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.serviceType = serviceType;
        this.available = true;
    }
    
    // Getter und Setter
    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    
    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
    
    public ServiceType getServiceType() { return serviceType; }
    public void setServiceType(ServiceType serviceType) { this.serviceType = serviceType; }
    
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
} 