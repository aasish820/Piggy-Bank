package com.piggybank.user.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.piggybank.user.entity.Role;
import com.piggybank.user.entity.User;
import com.piggybank.user.exception.RoleNotFoundException;
import com.piggybank.user.exception.UserNotFoundException;
import com.piggybank.user.model.UserDTO;
import com.piggybank.user.repo.RoleRepository;
import com.piggybank.user.repo.UserRepository;
import com.piggybank.user.service.UserServices;

@Service
public class UserServiceImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        if (userDTO.getRoles() == null || userDTO.getRoles().isEmpty()) {
            Role defaultRole = roleRepository.findByName("USER")
                    .orElseThrow(() -> new RoleNotFoundException("Default role not found"));
            userDTO.setRoles(Set.of(defaultRole.getName()));
        } else {
            Set<String> roleNames = userDTO.getRoles();
            Set<Role> roles = roleNames.stream()
                .map(roleName -> roleRepository.findByName(roleName)
                        .orElseThrow(() -> new RoleNotFoundException("Role not found: " + roleName)))
                .collect(Collectors.toSet());
            userDTO.setRoles(roles.stream().map(Role::getName).collect(Collectors.toSet()));
        }

        // Encode the password before saving
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        // Save the user
        User savedUser = userRepository.save(convertToEntity(userDTO));
        return convertToDTO(savedUser);
    }

    @Override
    public UserDTO getUserByUserName(String userName) {
        // Retrieve user by username, throw an exception if not found
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new UserNotFoundException("User not Found"));
        return convertToDTO(user);
    }

    @Override
    public List<UserDTO> getAllUser() {
        // Retrieve all users, filtering out soft-deleted ones
        return userRepository.findAllByDeleteAtIsNull().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {  // Changed to Long for consistency
        // Retrieve user by ID, throw an exception if not found or soft-deleted
        User user = userRepository.findByIdAndDeleteAtIsNull(id)
                .orElseThrow(() -> new UserNotFoundException("User not Found"));
        return convertToDTO(user);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {  // Changed to Long for consistency
        // Retrieve the user by ID, throw an exception if not found
        User user = userRepository.findByIdAndDeleteAtIsNull(id)
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));

        // Update user details, only if they are provided
        if (userDTO.getFirstName() != null) user.setFirst_name(userDTO.getFirstName());
        if (userDTO.getMiddleName() != null) user.setMiddle_name(userDTO.getMiddleName());
        if (userDTO.getLastName() != null) user.setLast_name(userDTO.getLastName());
        if (userDTO.getAddress() != null) user.setAddress(userDTO.getAddress());
        if (userDTO.getEmail() != null) user.setEmail(userDTO.getEmail());
        if (userDTO.getUsername() != null) user.setUsername(userDTO.getUsername());

        // Update password if provided
        if (userDTO.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }

        if (userDTO.getDob() != null) user.setDob(userDTO.getDob());
        if (userDTO.getPhoneNumber() != null) user.setPhone_number(userDTO.getPhoneNumber());

        // Update roles if provided
        if (userDTO.getRoles() != null && !userDTO.getRoles().isEmpty()) {
            Set<Role> roles = userDTO.getRoles().stream()
                    .map(roleName -> roleRepository.findByName(roleName)
                            .orElseThrow(() -> new RoleNotFoundException("Role not found: " + roleName)))
                    .collect(Collectors.toSet());
            user.setRoles(roles);
        }

        // Save the updated user
        User updatedUser = userRepository.save(user);
        return convertToDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        // Perform a soft delete by setting the delete_at field
        Optional<User> userOpt = userRepository.findByIdAndDeleteAtIsNull(id);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setDelete_at(LocalDate.now());
            userRepository.save(user);
        } else {
            throw new UserNotFoundException("User not Found");
        }
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        // Reuse the registerUser logic for creating a new user
        return registerUser(userDTO);
    }

    // Helper method to convert UserDTO to User entity
    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setFirst_name(userDTO.getFirstName());
        user.setMiddle_name(userDTO.getMiddleName());
        user.setLast_name(userDTO.getLastName());
        user.setAddress(userDTO.getAddress());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setDob(userDTO.getDob());
        user.setPhone_number(userDTO.getPhoneNumber());

        if (userDTO.getRoles() != null && !userDTO.getRoles().isEmpty()) {
            Set<Role> roles = userDTO.getRoles().stream()
                    .map(roleName -> roleRepository.findByName(roleName)
                            .orElseThrow(() -> new RoleNotFoundException("Role not found: " + roleName)))
                    .collect(Collectors.toSet());
            user.setRoles(roles);
        }

        return user;
    }

    // Helper method to convert User entity to UserDTO
    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirst_name());
        userDTO.setMiddleName(user.getMiddle_name());
        userDTO.setLastName(user.getLast_name());
        userDTO.setAddress(user.getAddress());
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());
        userDTO.setDob(user.getDob());
        userDTO.setPhoneNumber(user.getPhone_number());

        if (user.getRoles() != null) {
            Set<String> roleNames = user.getRoles().stream()
                    .map(Role::getName)
                    .collect(Collectors.toSet());
            userDTO.setRoles(roleNames);
        }

        return userDTO;
    }
}

