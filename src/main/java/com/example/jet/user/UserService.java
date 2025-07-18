package com.example.jet.user;

import com.example.jet.transaction.TransactionRepository;
import com.example.jet.user.dto.SignInDTO;
import com.example.jet.user.dto.SignInResponseDTO;
import com.example.jet.user.dto.UsernameAvailableDTO;
import com.example.jet.utils.JwtUtil;
import com.example.jet.utils.PasswordHasher;
import com.example.jet.utils.PasswordVerifier;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;


    @Autowired
    public UserService(UserRepository userRepository, TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }

    public UserDTO createUser(String username, String password) throws BadRequestException {
        boolean userExists = this.userRepository.findByUsername(username).isPresent();
        if (userExists) {
            throw new BadRequestException("User with that username already exists.");
        }
        String hashedPassword = PasswordHasher.hashPassword(password);
        UserEntity newUserEntity = new UserEntity(username, hashedPassword, null);
        UserEntity savedUserEntity = userRepository.save(newUserEntity);
        return new UserDTO(savedUserEntity.getId(), savedUserEntity.getUsername(), savedUserEntity.getFirstLogin());
    }

    public SignInResponseDTO login(SignInDTO data) {
        UserEntity userEntity = this.userRepository.findByUsername(data.getUsername()).orElseThrow(() -> new BadCredentialsException("Wrong username or password"));
        String password = data.getPassword();
        boolean passwordMatch = PasswordVerifier.verifyPassword(userEntity.getPassword(), password);
        if (!passwordMatch) {
            throw new BadCredentialsException("Wrong username or password.");
        }

        Boolean isFirstLogin = userEntity.getFirstLogin();

        if (isFirstLogin == null) {
            userEntity.setFirstLogin(true);
            this.userRepository.save((userEntity));
        } else {
            userEntity.setFirstLogin(false);
            this.userRepository.save((userEntity));
        }


        String token = JwtUtil.generateToken(userEntity.getId(), userEntity.getUsername());
        UserDTO userDTO = new UserDTO(userEntity.getId(), userEntity.getUsername(), userEntity.getFirstLogin());
        return new SignInResponseDTO(token, userDTO);
    }

    public List<UserDTO> getUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream().map(userEntity -> new UserDTO(userEntity.getId(), userEntity.getUsername(), userEntity.getFirstLogin())).collect(Collectors.toList());
    }

    public UserEntity getUserById(UUID userId) {
        return this.userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public UserDTO getCurrentUser(UUID userId) {
        UserEntity user = this.userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return new UserDTO(user.getId(), user.getUsername(), user.getFirstLogin());
    }

    public UsernameAvailableDTO checkUsernameAvailable(String username) {
        boolean available = this.userRepository.findByUsername(username).isEmpty();
        return new UsernameAvailableDTO(available);
    }

}
