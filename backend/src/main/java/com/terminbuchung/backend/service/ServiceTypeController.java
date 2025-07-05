package com.terminbuchung.backend.service;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/services")
@CrossOrigin(origins = "http://localhost:3000")
public class ServiceTypeController {
    
    private final ServiceTypeRepository serviceTypeRepository;
    
    public ServiceTypeController(ServiceTypeRepository serviceTypeRepository) {
        this.serviceTypeRepository = serviceTypeRepository;
    }
    
    @GetMapping
    public List<ServiceTypeDTO> getAllServices() {
        return serviceTypeRepository.findByActiveTrue().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/categories")
    public List<String> getCategories() {
        return serviceTypeRepository.findDistinctCategories();
    }
    
    @GetMapping("/category/{category}")
    public List<ServiceTypeDTO> getServicesByCategory(@PathVariable String category) {
        return serviceTypeRepository.findByCategory(category).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/{id}")
    public ServiceTypeDTO getService(@PathVariable Long id) {
        return serviceTypeRepository.findById(id)
                .map(this::toDTO)
                .orElse(null);
    }
    
    // Mapping Entity -> DTO
    private ServiceTypeDTO toDTO(ServiceType serviceType) {
        ServiceTypeDTO dto = new ServiceTypeDTO();
        dto.setId(serviceType.getId());
        dto.setName(serviceType.getName());
        dto.setCategory(serviceType.getCategory());
        dto.setDurationMinutes(serviceType.getDurationMinutes());
        dto.setPrice(serviceType.getPrice());
        dto.setDescription(serviceType.getDescription());
        dto.setActive(serviceType.getActive());
        return dto;
    }
} 