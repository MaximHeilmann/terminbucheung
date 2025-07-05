package com.terminbuchung.backend.termin;

import com.terminbuchung.backend.customer.Customer;
import com.terminbuchung.backend.service.ServiceType;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "termine")
public class Termin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_type_id", nullable = false)
    private ServiceType serviceType;  // Welcher Service (Haarschnitt, Beratung, etc.)

    @Column(nullable = false)
    private LocalDateTime startTime;  // Startzeit des Termins
    
    @Column(nullable = false)
    private LocalDateTime endTime;    // Endzeit des Termins

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;        // Wer hat gebucht?
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TerminStatus status = TerminStatus.GEBUCHT;  // Status des Termins
    
    private String notes;             // Notizen zum Termin
    
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();  // Wann wurde gebucht?

    public Termin() {}

    // Getter und Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public ServiceType getServiceType() { return serviceType; }
    public void setServiceType(ServiceType serviceType) { this.serviceType = serviceType; }

    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    
    public TerminStatus getStatus() { return status; }
    public void setStatus(TerminStatus status) { this.status = status; }
    
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
} 