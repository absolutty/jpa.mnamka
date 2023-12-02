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
@Table(name = "foods")
public class FoodModel implements FieldValidator {

    public static final long NEW_FOOD_ID_INDICATOR = -1;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private String name;
    private Double price;
    private String description;
    @ManyToOne @JoinColumn(name = "food_type_id") private FoodTypeModel type;
    private String measurement;
    private String img_url;

    public FoodModel(boolean isNewFoodBeingCreated) {
        if (isNewFoodBeingCreated) {
            this.id = NEW_FOOD_ID_INDICATOR;
        }
    }

    @Override
    public boolean anyRequiredFieldIsEmpty() {
        return  (name == null) || (name.isEmpty());
    }

    public boolean isNewFoodBeingCreated() {
        return (this.id == NEW_FOOD_ID_INDICATOR);
    }

}
