package com.terminbuchung.backend.availability;

import jakarta.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Table(name = "business_hours")
public class BusinessHours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DayOfWeek dayOfWeek;     // MONDAY, TUESDAY, etc.
    
    @Column(nullable = false)
    private LocalTime openTime;      // 09:00
    
    @Column(nullable = false)
    private LocalTime closeTime;     // 18:00
    
    @Column(nullable = false)
    private Boolean active = true;   // Geöffnet/Geschlossen
    
    public BusinessHours() {}
    
    // Getter und Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public DayOfWeek getDayOfWeek() { return dayOfWeek; }
    public void setDayOfWeek(DayOfWeek dayOfWeek) { this.dayOfWeek = dayOfWeek; }
    
    public LocalTime getOpenTime() { return openTime; }
    public void setOpenTime(LocalTime openTime) { this.openTime = openTime; }
    
    public LocalTime getCloseTime() { return closeTime; }
    public void setCloseTime(LocalTime closeTime) { this.closeTime = closeTime; }
    
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
} 