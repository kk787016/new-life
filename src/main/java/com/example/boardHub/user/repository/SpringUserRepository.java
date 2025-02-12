package com.example.boardHub.user.repository;

import com.example.boardHub.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringUserRepository extends JpaRepository<User, Long> {

    // 예시: userId로 사용자 찾기 (커스텀 쿼리 메서드)

}