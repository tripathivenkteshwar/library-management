package com.library.system.librarymanagement.controller;

import com.library.system.librarymanagement.aspect.LogExecutionTime;
import com.library.system.librarymanagement.service.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class BorrowingController {

    @Autowired
    private BorrowingService borrowingService;

    @PostMapping("borrow/{bookId}/patron/{patronId}")
    @LogExecutionTime
    public ResponseEntity<?> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        borrowingService.borrowBook(bookId, patronId);
        return ResponseEntity.ok("Book "+ bookId+" borrowed successfully by patron: " + patronId);
    }

    @PutMapping("return/{bookId}/patron/{patronId}")
    @LogExecutionTime
    public ResponseEntity<?> returnBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        borrowingService.returnBook(bookId, patronId);
        return ResponseEntity.ok("Book "+ bookId+ "returned successfully by patron " + patronId);
    }
}