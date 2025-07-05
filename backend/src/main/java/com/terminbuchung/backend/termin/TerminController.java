package com.terminbuchung.backend.termin;

import com.terminbuchung.backend.customer.CustomerDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/termine")
public class TerminController {
    private final TerminService terminService;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public TerminController(TerminService terminService) {
        this.terminService = terminService;
    }

    @GetMapping
    public List<TerminDTO> getAllTermine() {
        return terminService.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Mapping Methode Entity -> DTO (professionell)
    private TerminDTO toDTO(Termin termin) {
        TerminDTO dto = new TerminDTO();
        dto.setId(termin.getId());
        dto.setTitel(termin.getServiceType().getName());
        
        // Benutzerfreundliche Datumsformatierung
        dto.setStartzeit(termin.getStartTime().format(FORMATTER));
        dto.setEndzeit(termin.getEndTime().format(FORMATTER));
        
        // Nested Customer Object (professionell)
        if (termin.getCustomer() != null) {
            CustomerDTO kundeDTO = new CustomerDTO();
            kundeDTO.setId(termin.getCustomer().getId());
            kundeDTO.setVorname(termin.getCustomer().getVorname());
            kundeDTO.setNachname(termin.getCustomer().getNachname());
            kundeDTO.setEmail(termin.getCustomer().getEmail());
            dto.setKunde(kundeDTO);
        }
        
        return dto;
    }
} 