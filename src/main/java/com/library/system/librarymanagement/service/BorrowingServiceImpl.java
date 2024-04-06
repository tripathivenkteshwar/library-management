package com.library.system.librarymanagement.service;

import com.library.system.librarymanagement.entity.Book;
import com.library.system.librarymanagement.entity.BorrowingRecord;
import com.library.system.librarymanagement.entity.Patron;
import com.library.system.librarymanagement.exception.BookNotAvailableException;
import com.library.system.librarymanagement.exception.ResourceNotFoundException;
import com.library.system.librarymanagement.repository.BookRepository;
import com.library.system.librarymanagement.repository.BorrowingRecordRepository;
import com.library.system.librarymanagement.repository.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class BorrowingServiceImpl implements BorrowingService {

    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PatronRepository patronRepository;

    @Override
    @Transactional
    public void borrowBook(Long bookId, Long patronId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));
        Patron patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new ResourceNotFoundException("Patron not found with id: " + patronId));

        // Check if the book is available for borrowing
//        if (!book.isAvailableForBorrowing()) {
//            throw new BookNotAvailableException("Book with id " + bookId + " is not available for borrowing");
//        }

        // Logic to handle borrowing a book by a patron
        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBorrowDate(LocalDate.now());

        borrowingRecordRepository.save(borrowingRecord);
        //book.setAvailableForBorrowing(false);
        bookRepository.save(book);
    }

    @Override
    @Transactional
    public void returnBook(Long bookId, Long patronId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));
        Patron patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new ResourceNotFoundException("Patron not found with id: " + patronId));

        // Logic to handle returning a borrowed book by a patron
        BorrowingRecord borrowingRecord = borrowingRecordRepository.findByBookAndPatronAndReturnDateIsNull(book, patron);
        if (borrowingRecord == null) {
            throw new ResourceNotFoundException("No borrowing record found for book with id " + bookId + " and patron with id " + patronId);
        }

        borrowingRecord.setReturnDate(LocalDate.now());
        borrowingRecordRepository.save(borrowingRecord);
        //book.setAvailableForBorrowing(true);
        bookRepository.save(book);
    }
}