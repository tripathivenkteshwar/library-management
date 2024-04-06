package com.library.system.librarymanagement.service;

import com.library.system.librarymanagement.entity.Book;
import com.library.system.librarymanagement.entity.BorrowingRecord;
import com.library.system.librarymanagement.entity.Patron;
import com.library.system.librarymanagement.exception.ResourceNotFoundException;
import com.library.system.librarymanagement.repository.BookRepository;
import com.library.system.librarymanagement.repository.BorrowingRecordRepository;
import com.library.system.librarymanagement.repository.PatronRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BorrowingServiceTest {

    @Mock
    private BorrowingRecordRepository borrowingRecordRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private PatronRepository patronRepository;

    @InjectMocks
    private BorrowingServiceImpl borrowingService;

    @Test
    void borrowBook_Success() {
        // Given
        Long bookId = 1L;
        Long patronId = 1L;
        Book book = new Book();
        book.setId(bookId);
        Patron patron = new Patron();
        patron.setId(patronId);
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(patronRepository.findById(patronId)).thenReturn(Optional.of(patron));

        // When
        borrowingService.borrowBook(bookId, patronId);

        // Then
        verify(borrowingRecordRepository, times(1)).save(any(BorrowingRecord.class));
    }

    @Test
    void borrowBook_BookNotFound_ThrowsResourceNotFoundException() {
        // Given
        Long bookId = 1L;
        Long patronId = 1L;
        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        // When / Then
        assertThrows(ResourceNotFoundException.class, () -> borrowingService.borrowBook(bookId, patronId));
        verify(borrowingRecordRepository, never()).save(any(BorrowingRecord.class));
    }
}
