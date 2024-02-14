package com.ijse.dbms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ijse.dbms.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {
     @Query("select a from Admin a where a.userName = :userName")
    Optional<Admin> findByName(@Param("userName") String userName);

    Admin findByUserName(String userName);

    Boolean existsByUserName(String userName);

    @Query("select a.id from Admin a where a.userName = :userName")
    Long findIdByUserName(@Param("userName") String userName);
    
} 
