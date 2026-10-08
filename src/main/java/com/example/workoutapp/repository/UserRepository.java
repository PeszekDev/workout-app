package com.example.workoutapp.repository;

import com.example.workoutapp.dto.UserDto;
import com.example.workoutapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
