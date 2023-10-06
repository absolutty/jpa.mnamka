package sk.uniza.fri.mnamka.model;

import jakarta.persistence.*;
import sk.uniza.fri.mnamka.FieldValidator;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "users")
public class UserModel implements FieldValidator {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Integer id;
    private String email;
    private String name;
    private String lastName;
    private String password;
    private LocalDate dateOfRegistration;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(LocalDate dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    @Override
    public boolean anyRequiredFieldIsEmpty() {
        return getName() == null || getName().isEmpty() ||
               getLastName() == null || getLastName().isEmpty() ||
               getPassword() == null || getPassword().isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel that = (UserModel) o;
        return Objects.equals(id, that.id) && Objects.equals(password, that.password) && Objects.equals(email, that.email) && Objects.equals(name, that.name) && Objects.equals(lastName, that.lastName) && Objects.equals(dateOfRegistration, that.dateOfRegistration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, password, email, name, lastName, dateOfRegistration);
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfRegistration=" + dateOfRegistration +
                '}';
    }
}
