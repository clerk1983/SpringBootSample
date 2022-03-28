package com.example.repository;

import com.example.domain.user.model.MUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<MUser, String> {
}
