package sk.uniza.fri.mnamka.service;

import org.springframework.stereotype.Service;
import sk.uniza.fri.mnamka.model.User;
import sk.uniza.fri.mnamka.repository.UserRepository;


@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User getUserByEmail(String email){
        User user = userRepository.findUserByEmail(email);
        return user;
    }

    public User createUser(User user){
        User newUser = userRepository.save(user);
        userRepository.flush();
        return newUser;
    }
}
