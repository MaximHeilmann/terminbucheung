package com.terminbuchung.backend.booking;

import java.time.LocalDateTime;

public class BookingRequest {
    private Long serviceTypeId;
    private Long customerId;
    private LocalDateTime startTime;
    private String notes;
    
    // Customer-Daten für neue Kunden
    private String customerVorname;
    private String customerNachname;
    private String customerEmail;
    
    public BookingRequest() {}
    
    // Getter und Setter
    public Long getServiceTypeId() { return serviceTypeId; }
    public void setServiceTypeId(Long serviceTypeId) { this.serviceTypeId = serviceTypeId; }
    
    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }
    
    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    
    public String getCustomerVorname() { return customerVorname; }
    public void setCustomerVorname(String customerVorname) { this.customerVorname = customerVorname; }
    
    public String getCustomerNachname() { return customerNachname; }
    public void setCustomerNachname(String customerNachname) { this.customerNachname = customerNachname; }
    
    public String getCustomerEmail() { return customerEmail; }
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }
    
    // Validation
    public boolean isNewCustomer() {
        return customerId == null && customerVorname != null && customerEmail != null;
    }
    
    public boolean isValid() {
        return serviceTypeId != null && startTime != null && 
               (customerId != null || isNewCustomer());
    }
} 