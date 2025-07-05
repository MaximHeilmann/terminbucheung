package com.terminbuchung.backend.availability;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

public interface BusinessHoursRepository extends JpaRepository<BusinessHours, Long> {
    
    List<BusinessHours> findByActiveTrue();
    
    Optional<BusinessHours> findByDayOfWeekAndActiveTrue(DayOfWeek dayOfWeek);
    
    List<BusinessHours> findByDayOfWeekIn(List<DayOfWeek> days);
} 