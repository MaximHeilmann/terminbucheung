package com.terminbuchung.backend.booking;

import com.terminbuchung.backend.termin.TerminDTO;
import com.terminbuchung.backend.customer.CustomerDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/booking")
@CrossOrigin(origins = "http://localhost:3000")
public class BookingController {
    
    private final BookingService bookingService;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    
    /**
     * Bucht einen neuen Termin
     * POST /api/booking
     */
    @PostMapping
    public ResponseEntity<?> bookTermin(@RequestBody BookingRequest request) {
        BookingResult result = bookingService.bookTermin(request);
        
        if (result.isSuccess()) {
            return ResponseEntity.ok(new BookingResponse(
                true, 
                result.getMessage(), 
                toTerminDTO(result.getTermin())
            ));
        } else {
            return ResponseEntity.badRequest().body(new BookingResponse(
                false, 
                result.getMessage(), 
                null
            ));
        }
    }
    
    /**
     * Storniert einen Termin
     * DELETE /api/booking/{terminId}
     */
    @DeleteMapping("/{terminId}")
    public ResponseEntity<?> cancelTermin(@PathVariable Long terminId) {
        BookingResult result = bookingService.cancelTermin(terminId);
        
        if (result.isSuccess()) {
            return ResponseEntity.ok(new BookingResponse(
                true, 
                "Termin erfolgreich storniert", 
                toTerminDTO(result.getTermin())
            ));
        } else {
            return ResponseEntity.badRequest().body(new BookingResponse(
                false, 
                result.getMessage(), 
                null
            ));
        }
    }
    
    // Mapping Entity -> DTO
    private TerminDTO toTerminDTO(com.terminbuchung.backend.termin.Termin termin) {
        if (termin == null) return null;
        
        TerminDTO dto = new TerminDTO();
        dto.setId(termin.getId());
        dto.setTitel(termin.getServiceType().getName());
        dto.setStartzeit(termin.getStartTime().format(FORMATTER));
        dto.setEndzeit(termin.getEndTime().format(FORMATTER));
        
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
    
    // Response-Klasse für einheitliche API-Antworten
    public static class BookingResponse {
        private boolean success;
        private String message;
        private TerminDTO termin;
        
        public BookingResponse(boolean success, String message, TerminDTO termin) {
            this.success = success;
            this.message = message;
            this.termin = termin;
        }
        
        // Getter
        public boolean isSuccess() { return success; }
        public String getMessage() { return message; }
        public TerminDTO getTermin() { return termin; }
    }
} 