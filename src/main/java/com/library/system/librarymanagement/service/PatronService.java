package com.library.system.librarymanagement.service;

import com.library.system.librarymanagement.entity.Patron;

import java.util.List;

public interface PatronService {
    List<Patron> getAllPatrons();
    Patron getPatronById(Long id);
    Patron addPatron(Patron patron);
    Patron updatePatron(Long id, Patron patronDetails);
    void deletePatron(Long id);
}
