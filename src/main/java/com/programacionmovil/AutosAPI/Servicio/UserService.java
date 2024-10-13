package com.programacionmovil.AutosAPI.Servicio;

import com.programacionmovil.AutosAPI.Modelo.User;
import com.programacionmovil.AutosAPI.Repositorio.UserRepository;
import java.util.Optional;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger logger = Logger.getLogger(UserService.class.getName());
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> authenticate(String username, String password) {
        logger.info("Authenticating user: " + username);
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            logger.info("User found: " + username);
            if (passwordEncoder.matches(password, userOpt.get().getPassword())) {
                logger.info("Password matches for user: " + username);
                return userOpt;
            } else {
                logger.info("Password does not match for user: " + username);
            }
        } else {
            logger.info("User not found: " + username);
        }
        return Optional.empty();
    }

    public User findUserById(Integer id) {
        logger.info("Finding user by ID: " + id);
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            logger.info("User found: " + userOpt.get());
        } else {
            logger.warning("User not found with ID: " + id);
        }
        return userOpt.orElse(null);
    }

}
