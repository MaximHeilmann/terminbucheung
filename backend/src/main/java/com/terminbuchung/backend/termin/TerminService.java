package com.terminbuchung.backend.termin;

import com.terminbuchung.backend.termin.Termin;
import com.terminbuchung.backend.termin.TerminRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TerminService {
    private final TerminRepository terminRepository;

    public TerminService(TerminRepository terminRepository) {
        this.terminRepository = terminRepository;
    }

    public List<Termin> findAll() {
        return terminRepository.findAll();
    }

    public Termin save(Termin termin) {
        return terminRepository.save(termin);
    }
} 