package sk.uniza.fri.mnamka.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sk.uniza.fri.mnamka.helper.FieldValidator;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "food_types")
public class FoodTypeModel implements FieldValidator {

    public static final long NEW_FOODTYPE_ID_INDICATOR = -1;

    @Id @Column(name="ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY) private Long id;
    @Column(unique = true, nullable = false) private String name;

    public FoodTypeModel(boolean isNewFoodTypeBeingCreated) {
        if (isNewFoodTypeBeingCreated) {
            this.id = NEW_FOODTYPE_ID_INDICATOR;
        }
    }

    @Override
    public boolean anyRequiredFieldIsEmpty() {
        return (name == null || name.isEmpty());
    }

    public boolean isNewFoodTypeBeingCreated() {
        return (this.id == NEW_FOODTYPE_ID_INDICATOR);
    }

}
