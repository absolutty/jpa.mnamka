package sk.uniza.fri.mnamka.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sk.uniza.fri.mnamka.helper.FieldValidator;

import java.util.Collection;
import java.util.Collections;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USERS")
public class User implements FieldValidator, UserDetails {

    public static final String ROLE_USER = "USER";
    public static final String ROLE_ADMIN = "ADMIN";
    public static final long NEW_USER_ID_INDICATOR = -1;

    @Id @Column(name = "ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY) private Long id;
    @Column(name = "EMAIL") private String email;
    @Column(name = "PASSWORD") private String password;
    @Column(name = "FIRST_NAME") private String firstName;
    @Column(name = "LAST_NAME") private String lastName;
    @Column(name = "GENDER") private String gender;
    @Column(name = "ROLE") private String role;

    public User(boolean isNewUserBeingCreated) {
        if (isNewUserBeingCreated) {
            this.id = NEW_USER_ID_INDICATOR;
        }
    }

    public User(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public boolean anyRequiredFieldIsEmpty() {
        return  (email == null || email.isEmpty()) ||
                (password == null || password.isEmpty()) ||
                (firstName == null || firstName.isEmpty()) ||
                (lastName == null || lastName.isEmpty());
    }

    public boolean isNewUserBeingCreated() {
        return (this.id == NEW_USER_ID_INDICATOR);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
