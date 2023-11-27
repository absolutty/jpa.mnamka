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

    public User authenticateUser(User user) {
        User userFromDatabase = userRepository.userAuthentication(user.getEmail(), user.getPassword());
        if (userFromDatabase == null) {
            throw new BadCredentialsException("Not correct user credentials!");
        } else {
            return userFromDatabase;
        }
    }

    public User createUser(User user){
        User userFromDatabase = userRepository.findUserByEmail(user.getEmail());
        if (userFromDatabase == null) {
            User newUser = userRepository.save(user);
            userRepository.flush();
            return newUser;
        } else {
            throw new BadCredentialsException("Email to identify user already exists!");
        }
    }

}
