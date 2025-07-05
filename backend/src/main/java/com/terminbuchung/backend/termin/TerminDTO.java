package com.terminbuchung.backend.termin;

import com.terminbuchung.backend.customer.CustomerDTO;

public class TerminDTO {
    private Long id;
    private String titel;
    private String startzeit;
    private String endzeit;
    private CustomerDTO kunde;

    // GETTER
    public Long getId() { return id; }
    public String getTitel() { return titel; }
    public String getStartzeit() { return startzeit; }
    public String getEndzeit() { return endzeit; }
    public CustomerDTO getKunde() { return kunde; }

    // SETTER
    public void setId(Long id) { this.id = id; }
    public void setTitel(String titel) { this.titel = titel; }
    public void setStartzeit(String startzeit) { this.startzeit = startzeit; }
    public void setEndzeit(String endzeit) { this.endzeit = endzeit; }
    public void setKunde(CustomerDTO kunde) { this.kunde = kunde; }
} 