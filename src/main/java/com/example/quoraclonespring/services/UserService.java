package com.example.quoraclonespring.services;

import com.example.quoraclonespring.dtos.UserDTO;
import com.example.quoraclonespring.models.Tag;
import com.example.quoraclonespring.models.User;
import com.example.quoraclonespring.repositories.TagRepository;
import com.example.quoraclonespring.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private TagRepository tagRepository;

    public UserService(UserRepository userRepository, TagRepository tagRepository) {
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public User createUser(UserDTO userDTO){
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        return userRepository.save(user);
    }
    public void  deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public void followTag(Long userId, Long tagId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Tag tag = tagRepository.findById(tagId).orElseThrow(() -> new RuntimeException("Tag not found"));
        user.getFollowedTags().add(tag);
        userRepository.save(user);
    }
}
