package com.library.system.librarymanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
public class BorrowingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long borrowingRecordId;

    @NotNull(message = "Book cannot be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    //insertable = false, updatable = false
    @NotNull(message = "Patron cannot be null")
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patron_id",nullable = false)
    private Patron patron;

    @NotNull(message = "Borrow date cannot be null")
    private LocalDate borrowDate;

    private LocalDate returnDate;

    // Getters and setters
}
