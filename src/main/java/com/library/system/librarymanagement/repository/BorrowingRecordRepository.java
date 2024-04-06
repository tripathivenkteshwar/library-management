package com.library.system.librarymanagement.repository;

import com.library.system.librarymanagement.entity.Book;
import com.library.system.librarymanagement.entity.BorrowingRecord;
import com.library.system.librarymanagement.entity.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {
    BorrowingRecord findByBookAndPatronAndReturnDateIsNull(Book book, Patron patron);

}

