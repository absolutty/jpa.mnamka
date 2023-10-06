package sk.uniza.fri.mnamka.service;

import org.springframework.stereotype.Service;
import sk.uniza.fri.mnamka.model.UserModel;
import sk.uniza.fri.mnamka.repository.UserRepository;

import java.time.LocalDate;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel registerUser(String name, String lastName, String email, String password) {
        UserModel userModel = new UserModel();

        userModel.setEmail(email);
        userModel.setName(name);
        userModel.setLastName(lastName);
        userModel.setPassword(password);
        userModel.setDateOfRegistration(LocalDate.now());

        if (userModel.anyRequiredFieldIsEmpty()) {
            throw new IllegalArgumentException(String.format("One or more of required fields are empty [%s]", userModel));
        }

        return userRepository.save(userModel);
    }

    public UserModel authenticate(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password).orElse(null);
    }

}
