package com.example.jet.user;

import com.example.jet.transaction.TransactionRepository;
import com.example.jet.utils.JwtUtil;
import com.example.jet.utils.PasswordHasher;
import com.example.jet.utils.PasswordVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    public UserService(UserRepository userRepository, TransactionRepository transactionRepository) {

        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }

    public UserDTO createUser(String username, String password) {
        String hashedPassword = PasswordHasher.hashPassword(password);
        UserEntity newUserEntity = new UserEntity(username, hashedPassword);
        UserEntity savedUserEntity = userRepository.save(newUserEntity);
        return new UserDTO(savedUserEntity.getId(), savedUserEntity.getUsername());
    }

    public SignInResponseDTO login(SignInDTO data) {
        UserEntity userEntity = this.userRepository.findByUsername(data.getUsername()).orElseThrow(() -> new IllegalArgumentException("Wrong username or password"));
        String password = data.getPassword();
        boolean passwordMatch = PasswordVerifier.verifyPassword(userEntity.getPassword(), password);
        if (!passwordMatch) {
            throw new IllegalArgumentException("Wrong username or password.");
        }
        String token = JwtUtil.generateToken(userEntity.getId(), userEntity.getUsername());
        UserDTO userDTO = new UserDTO(userEntity.getId(), userEntity.getUsername());
        return new SignInResponseDTO(token, userDTO);
    }

    public List<UserDTO> getUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream().map(userEntity -> new UserDTO(userEntity.getId(), userEntity.getUsername())).collect(Collectors.toList());
    }

    public UserEntity getUserById(UUID userId) {
        return this.userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }


}
