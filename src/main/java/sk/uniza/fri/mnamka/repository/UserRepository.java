package sk.uniza.fri.mnamka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sk.uniza.fri.mnamka.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    /**
     * Authenticates a user based on the provided email and password.
     * This method queries the database for a user with the given email and password combination.
     *
     * @param email    The email of the user to authenticate.
     * @param password The password of the user to authenticate.
     * @return If authentication is successful, returns the authenticated User object; otherwise, returns null.
     */
    @Query("SELECT u from User u WHERE u.email = :email AND u.password = :password")
    User userAuthentication(String email, String password);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findUserByEmail(@Param("email") String email);

    @Query("SELECT u.email FROM User u ORDER BY u.id")
    List<String> userEmails();

    @Modifying
    @Transactional
    @Query( "UPDATE User u " +
            "SET u.email = :newEmail, " +
                "u.firstName = :newFirstName, " +
                "u.lastName = :newLastName, " +
                "u.password = :newPassword, " +
                "u.gender = :newGender, " +
                "u.role = :newRole " +
            "WHERE u.id = :userId")
    void updateUserWithId(
            @Param("userId") Long userId,
            @Param("newEmail") String newEmail,
            @Param("newFirstName") String newFirstName,
            @Param("newLastName") String newLastName,
            @Param("newPassword") String newPassword,
            @Param("newGender") String newGender,
            @Param("newRole") String newRole
    );

    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.id = :userId AND u.email = :email")
    void deleteUserWithIdAndEmail(
            @Param("userId") Long userId,
            @Param("email") String email
    );

}
