package sk.uniza.fri.mnamka.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sk.uniza.fri.mnamka.model.User;
import sk.uniza.fri.mnamka.repository.UserRepository;

import java.util.List;

@Service
public class UserService  implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);

        if(user == null){
            throw new UsernameNotFoundException("No user found with email");
        }

        UserDetails userDetails =
                org.springframework.security.core.userdetails.User.builder()
                        .username(user.getEmail())
                        .password(user.getPassword())
                        .roles(user.getRole())
                        .build();

        return userDetails;
    }

    public User authenticateUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Given USER is null!");
        }

        User userFromDatabase = userRepository.userAuthentication(user.getEmail(), user.getPassword());
        if (userFromDatabase == null) {
            throw new BadCredentialsException("Not correct user credentials!");
        } else {
            return userFromDatabase;
        }
    }

    public User createUser(User user){
        if (user == null || user.anyRequiredFieldIsEmpty()) {
            throw new IllegalArgumentException("Given USER is not correct!");
        }

        User userAlreadyInDatabase = userRepository.findUserByEmail(user.getEmail());
        if (userAlreadyInDatabase == null) {
            User newUser = userRepository.save(user);
            userRepository.flush();

            return newUser;
        } else {
            throw new BadCredentialsException("Email to identify user already exists!");
        }
    }

    public List<String> getUsersIdentifiers() {
        return userRepository.userEmails();
    }

    public User getUserByEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Given EMAIL is null!");
        } else {
            return userRepository.findUserByEmail(email);
        }
    }

    public void updateExistingUser(User user) {
        if (user == null || user.anyRequiredFieldIsEmpty()) {
            throw new IllegalArgumentException("Given USER is not correct!");
        } else {
            userRepository.updateUserWithId(
                    user.getId(),
                    user.getEmail(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getPassword(),
                    user.getAddress(),
                    user.getPhoneNumber(),
                    user.getGender(),
                    user.getRole()
            );
        }
    }

    public void deleteExistingUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Given USER null!");
        } else {
            userRepository.deleteUserWithIdAndEmail(user.getId(), user.getEmail());
        }
    }

}
