package sk.uniza.fri.mnamka.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "ordered_foods")
public class OrderedFood extends AbstractEntity {

    private static Long STATIC_ID = 1L;

    @JoinColumn(name = "order_id", nullable = false) @ManyToOne private Order order;
    @JoinColumn(name = "food_id", nullable = false) @ManyToOne private FoodModel food;
    @Column(nullable = false) private Integer quantity;

    public OrderedFood(FoodModel foodModel, Integer quantity) {
        setId(STATIC_ID++);
        this.food = foodModel;
        this.quantity = quantity;
    }

    @Override
    public boolean anyRequiredFieldIsEmpty() {
        return super.anyRequiredFieldIsEmpty();
    }

    @Override
    public boolean anyNumberFieldIsNotCorrect() {
        return super.anyNumberFieldIsNotCorrect();
    }

}
