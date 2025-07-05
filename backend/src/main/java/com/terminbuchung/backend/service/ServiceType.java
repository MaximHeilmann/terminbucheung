package com.terminbuchung.backend.service;

import jakarta.persistence.*;

@Entity
@Table(name = "service_types")
public class ServiceType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;           // "Haarschnitt", "Beratung", "Untersuchung"
    
    @Column(nullable = false)
    private String category;       // "Friseur", "Arzt", "Beratung"
    
    @Column(nullable = false)
    private Integer durationMinutes;  // Dauer in Minuten
    
    @Column(nullable = false)
    private Double price;          // Preis
    
    private String description;    // Beschreibung
    
    @Column(nullable = false)
    private Boolean active = true; // Aktiv/Inaktiv
    
    public ServiceType() {}
    
    // Getter und Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public Integer getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(Integer durationMinutes) { this.durationMinutes = durationMinutes; }
    
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
} 