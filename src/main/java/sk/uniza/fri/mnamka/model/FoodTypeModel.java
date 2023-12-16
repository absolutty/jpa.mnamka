package sk.uniza.fri.mnamka.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import sk.uniza.fri.mnamka.helper.FieldValidator;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "food_types")
public class FoodTypeModel extends AbstractEntity {

    @Column(unique = true, nullable = false) private String name;

    public FoodTypeModel(boolean isNewFoodTypeBeingCreated) {
        super(isNewFoodTypeBeingCreated);
    }

    @Override
    public boolean anyRequiredFieldIsEmpty() {
        return (name == null || name.isEmpty());
    }

}
