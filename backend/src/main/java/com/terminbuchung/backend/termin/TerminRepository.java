package com.terminbuchung.backend.termin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface TerminRepository extends JpaRepository<Termin, Long> {
    
    List<Termin> findByCustomerId(Long customerId);
    
    List<Termin> findByStatus(TerminStatus status);
    
    @Query("SELECT t FROM Termin t WHERE t.startTime >= :start AND t.endTime <= :end")
    List<Termin> findByDateRange(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
    
    @Query("SELECT t FROM Termin t WHERE t.startTime >= :start AND t.startTime < :end AND t.status != 'STORNIERT'")
    List<Termin> findActiveTermineInDateRange(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
    
    @Query("SELECT COUNT(t) > 0 FROM Termin t WHERE " +
           "((t.startTime >= :start AND t.startTime < :end) OR " +
           "(t.endTime > :start AND t.endTime <= :end) OR " +
           "(t.startTime <= :start AND t.endTime >= :end)) AND " +
           "t.status != 'STORNIERT'")
    boolean hasConflictingTermin(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
} 