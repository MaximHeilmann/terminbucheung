package com.terminbuchung.backend.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ServiceTypeRepository extends JpaRepository<ServiceType, Long> {
    
    List<ServiceType> findByActiveTrue();
    
    List<ServiceType> findByCategory(String category);
    
    @Query("SELECT DISTINCT s.category FROM ServiceType s WHERE s.active = true")
    List<String> findDistinctCategories();
} 