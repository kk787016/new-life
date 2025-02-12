package com.example.boardHub.user.repository;

import com.example.boardHub.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends JpaRepository<User,Long> {

}
