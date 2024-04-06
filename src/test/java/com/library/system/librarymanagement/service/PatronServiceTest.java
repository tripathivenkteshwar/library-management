package com.library.system.librarymanagement.service;

import com.library.system.librarymanagement.entity.Patron;
import com.library.system.librarymanagement.exception.ResourceNotFoundException;
import com.library.system.librarymanagement.repository.PatronRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PatronServiceTest {

    @Mock
    private PatronRepository patronRepository;

    @InjectMocks
    private PatronServiceImpl patronService;

    @Test
    void testGetAllPatrons() {
        // Given
        List<Patron> patrons = new ArrayList<>();
        patrons.add(new Patron());
        patrons.add(new Patron());
        when(patronRepository.findAll()).thenReturn(patrons);

        // When
        List<Patron> result = patronService.getAllPatrons();

        // Then
        assertEquals(2, result.size());
        verify(patronRepository, times(1)).findAll();
    }

}