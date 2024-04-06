package com.library.system.librarymanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be blank")
    @Size(max = 100, message = "Title cannot exceed 100 characters")
    private String title;

    @NotBlank(message = "Author cannot be blank")
    @Size(max = 50, message = "Author cannot exceed 50 characters")
    private String author;

    @NotNull(message = "Publication year cannot be null")
    @Positive(message = "Publication year must be a positive value")
    private Integer publicationYear;

    @NotBlank(message = "ISBN cannot be blank")
    @Size(max = 20, message = "ISBN cannot exceed 20 characters")
    private String isbn;

    private boolean availableForBorrowing = true;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BorrowingRecord> borrowingRecords = new ArrayList<>();

}

