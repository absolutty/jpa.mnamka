package sk.uniza.fri.mnamka.model;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "foods")
public class FoodModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long food_id;
    private String name;
    private Double price;
    private String description;
    @ManyToOne @JoinColumn(name = "food_type_id") private FoodTypeModel type;
    private String measurement;

    public Long getFood_id() {
        return food_id;
    }
    public void setFood_id(Long food_id) {
        this.food_id = food_id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public FoodTypeModel getType() {
        return type;
    }
    public void setType(FoodTypeModel type) {
        this.type = type;
    }

    public String getMeasurement() {
        return measurement;
    }
    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodModel foodModel = (FoodModel) o;
        return Objects.equals(food_id, foodModel.food_id) && Objects.equals(name, foodModel.name) && Objects.equals(price, foodModel.price) && Objects.equals(description, foodModel.description) && Objects.equals(type, foodModel.type) && Objects.equals(measurement, foodModel.measurement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(food_id, name, price, description, type, measurement);
    }

    @Override
    public String toString() {
        return "FoodModel{" +
                "food_id=" + food_id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", measurement='" + measurement + '\'' +
                '}';
    }

}
