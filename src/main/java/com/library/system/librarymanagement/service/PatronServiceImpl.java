package com.library.system.librarymanagement.service;

import com.library.system.librarymanagement.entity.Patron;
import com.library.system.librarymanagement.exception.ResourceNotFoundException;
import com.library.system.librarymanagement.repository.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PatronServiceImpl implements PatronService {

    @Autowired
    private PatronRepository patronRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Patron> getAllPatrons() {
        return patronRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Patron getPatronById(Long id) {
        return patronRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patron not found with id: " + id));
    }

    @Override
    @Transactional
    public Patron addPatron(Patron patron) {
        return patronRepository.save(patron);
    }

    @Override
    @Transactional
    public Patron updatePatron(Long id, Patron patronDetails) {
        Patron patron = patronRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patron not found with id: " + id));
        patron.setName(patronDetails.getName());
        patron.setEmail(patronDetails.getEmail()); // Use setEmail method
        patron.setPhoneNumber(patronDetails.getPhoneNumber()); // Use setPhoneNumber method
        return patronRepository.save(patron);
    }

    @Override
    @Transactional
    public void deletePatron(Long id) {
        patronRepository.deleteById(id);
    }
}
