package com.example.jet.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    @Query("SELECT t FROM Transaction t WHERE t.user.id = :userId and t.type =:type AND t.date BETWEEN :start AND :end")
    List<Transaction> findByPeriod(@Param("userId") UUID userId, @Param("type") TransactionType type, @Param("start") LocalDate start, @Param("end") LocalDate end);

}
