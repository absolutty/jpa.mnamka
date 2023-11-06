package sk.uniza.fri.mnamka.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "food_types")
public class FoodTypeModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(unique = true, nullable = false) private String name;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
        FoodTypeModel that = (FoodTypeModel) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "FoodTypeModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
