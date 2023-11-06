package sk.uniza.fri.mnamka.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "allergens")
public class AllergenModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long allergen_id;
    @Column(unique = true, nullable = false) private String name;

    public Long getAllergen_id() {
        return allergen_id;
    }
    public void setAllergen_id(Long id) {
        this.allergen_id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AllergenModel that = (AllergenModel) o;
        return Objects.equals(allergen_id, that.allergen_id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(allergen_id, name);
    }

    @Override
    public String toString() {
        return "AllergenModel{" +
                "id=" + allergen_id +
                ", name='" + name + '\'' +
                '}';
    }
}
