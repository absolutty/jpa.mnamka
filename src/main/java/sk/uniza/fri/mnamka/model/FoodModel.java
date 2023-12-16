package sk.uniza.fri.mnamka.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "foods")
public class FoodModel extends AbstractEntity {

    @Column(unique = true, nullable = false) private String name;
    @Column(nullable = false) private Double price;
    @Column private String description;
    @JoinColumn(name = "food_type_id", nullable = false) @ManyToOne private FoodTypeModel type;
    @Column(nullable = false) private String measurement;
    @Column private String img_url;

    public FoodModel(boolean isNewFoodBeingCreated) {
        super(isNewFoodBeingCreated);
    }

    @Override
    public boolean anyRequiredFieldIsEmpty() {
        return  (name == null)  || (name.isEmpty()) ||
                (price == null) || (price.isNaN()) ||
                (measurement == null) || (measurement.isEmpty());
    }

    @Override
    public boolean anyNumberFieldIsNotCorrect() {
        return (price == null) || (price <= 0);
    }

}
