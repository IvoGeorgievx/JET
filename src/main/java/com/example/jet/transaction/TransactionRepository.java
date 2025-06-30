package com.example.jet.transaction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, UUID> {

    @Query("SELECT t FROM TransactionEntity t WHERE t.userEntity.id = :userId and t.type = :type AND t.date BETWEEN :start AND :end")
    List<TransactionEntity> findByPeriod(@Param("userId") UUID userId, @Param("type") TransactionType type, @Param("start") LocalDate start, @Param("end") LocalDate end);

    @Query("Select t FROM TransactionEntity t WHERE t.userEntity.id =:userId AND t.date BETWEEN :start AND :end")
    Page<TransactionEntity> findByUserEntity(@Param("userId") UUID userId, Pageable pageable, @Param("start") LocalDate start, @Param("end") LocalDate end);

    @Query("Select t FROM TransactionEntity t WHERE t.userEntity.id =:userId AND t.date BETWEEN :start AND :end")
    List<TransactionEntity> findRawByUserEntity(@Param("userId") UUID userId, @Param("start") LocalDate start, @Param("end") LocalDate end);

}
