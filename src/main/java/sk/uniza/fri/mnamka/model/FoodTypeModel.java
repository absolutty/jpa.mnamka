package sk.uniza.fri.mnamka.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "food_types")
public class FoodTypeModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long food_type_id;
    @Column(unique = true, nullable = false) private String name;

    public Long getFood_type_id() {
        return food_type_id;
    }
    public void setFood_type_id(Long id) {
        this.food_type_id = id;
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
        return Objects.equals(food_type_id, that.food_type_id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(food_type_id, name);
    }

    @Override
    public String toString() {
        return "FoodTypeModel{" +
                "id=" + food_type_id +
                ", name='" + name + '\'' +
                '}';
    }

}
