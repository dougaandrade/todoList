package com.ignis.to_do.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ignis.to_do.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByNameAndEmail(String name, String email);
    boolean existsByNameAndEmailAndPassword(String name, String email, String password);

    @Modifying
    @Query("UPDATE users u SET u.name = :name, u.email = :email, u.password = :password WHERE u.id = :id")
    void updateUser(
        @Param("id") long id,
        @Param("name") String name,
        @Param("email") String email,
        @Param("password") String password
        );
}
