package com.terminbuchung.backend.termin;

public enum TerminStatus {
    GEBUCHT("Gebucht"),
    BESTAETIGT("Bestätigt"),
    ABGESCHLOSSEN("Abgeschlossen"),
    STORNIERT("Storniert"),
    NICHT_ERSCHIENEN("Nicht erschienen");
    
    private final String displayName;
    
    TerminStatus(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
} 