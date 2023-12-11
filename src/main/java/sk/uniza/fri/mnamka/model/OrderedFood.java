package sk.uniza.fri.mnamka.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ORDERED_FOODS")
public class OrderedFood {

    private static Long IN_SESSION_ID = 0L;

    @Id @Column(name = "ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FOOD_ID", nullable = false)
    private FoodModel food;

    @Column(name = "QUANTITY", nullable = false)
    private Integer quantity;

    public static Long getNextvalId() {
        return ++IN_SESSION_ID;
    }

}
