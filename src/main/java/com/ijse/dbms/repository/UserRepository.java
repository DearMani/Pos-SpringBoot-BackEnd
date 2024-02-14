package com.ijse.dbms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ijse.dbms.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    //custom queries
    
    @Query("select u from User u where u.userName = :userName")
    Optional<User> findByName(@Param("userName") String userName);

    User findByUserName(String userName);

    Boolean existsByUserName(String userName);

    Boolean existsByEmail(String email);

    @Query("select u.id from User u where u.userName = :userName")
    Long findIdByUserName(@Param("userName") String userName);

}