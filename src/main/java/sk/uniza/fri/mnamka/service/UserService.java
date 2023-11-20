package sk.uniza.fri.mnamka.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import sk.uniza.fri.mnamka.model.User;
import sk.uniza.fri.mnamka.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User createUser(User user){
        User existingUser = userRepository.findUserByEmail(user.getEmail());
        if (existingUser == null) {
            User newUser = userRepository.save(user);
            userRepository.flush();
            return newUser;
        } else {
            throw new BadCredentialsException("Email to identify user already exists!");
        }
    }
}
