package com.example.workoutapp.service;


import com.example.workoutapp.dto.UserDto;
import com.example.workoutapp.model.User;
import com.example.workoutapp.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Comparator;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(user -> new UserDto(user.getId(), user.getName(), user.getEmail()))
                .sorted(Comparator.comparing(UserDto::getId))
                .toList();
    }

    public UserDto getUserById(Long id ) {
        return userRepository.findById(id)
                .map(user -> new UserDto(user.getId() ,user.getName(),user.getEmail()))
                .orElseThrow(() -> new IllegalStateException("nie ma użytkownika o id " +id ));
    }

    public void addUser(UserDto userDto) {
        User user = new User(userDto.getName() , userDto.getEmail());
        userRepository.save(user);
    }

    @Transactional
    public void updateUser(Long userId , String name , String email) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("id " + userId + " nie istnieje"));
        if(name != null && !name.isBlank() && !name.equals(user.getName())) {
            user.setName(name);
        }
        if(email != null && !email.isBlank() && !email.equals(user.getEmail())) {
            user.setEmail(email);
        }
        userRepository.save(user);
    }

}
