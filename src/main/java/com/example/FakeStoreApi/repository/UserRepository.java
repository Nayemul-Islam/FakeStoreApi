package com.example.FakeStoreApi.repository;

import com.example.FakeStoreApi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
