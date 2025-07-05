package com.terminbuchung.backend.booking;

import com.terminbuchung.backend.termin.Termin;

public class BookingResult {
    private boolean success;
    private String message;
    private Termin termin;
    
    private BookingResult(boolean success, String message, Termin termin) {
        this.success = success;
        this.message = message;
        this.termin = termin;
    }
    
    public static BookingResult success(Termin termin) {
        return new BookingResult(true, "Termin erfolgreich gebucht", termin);
    }
    
    public static BookingResult error(String message) {
        return new BookingResult(false, message, null);
    }
    
    // Getter
    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public Termin getTermin() { return termin; }
} 