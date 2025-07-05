package com.terminbuchung.backend.service;

public class ServiceTypeDTO {
    private Long id;
    private String name;
    private String category;
    private Integer durationMinutes;
    private Double price;
    private String description;
    private Boolean active;
    
    public ServiceTypeDTO() {}
    
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
    
    // Convenience methods
    public String getDurationFormatted() {
        if (durationMinutes == null) return "";
        int hours = durationMinutes / 60;
        int minutes = durationMinutes % 60;
        if (hours > 0) {
            return hours + "h " + (minutes > 0 ? minutes + "min" : "");
        }
        return minutes + "min";
    }
    
    public String getPriceFormatted() {
        return price != null ? String.format("%.2f €", price) : "0.00 €";
    }
} 