package sk.uniza.fri.mnamka.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends AbstractEntity {

    @JoinColumn(name = "user_ID") @ManyToOne private User user;
    @Column(nullable = false) Date creationDate;
    @Column(nullable = false) OrderState orderState;

    public enum OrderState {

        PENDING("Čakajúca", "Objednávka začína byť SPRACOVÁVANÁ..."),
        PROCESSING("Spracovávaná", "Objednávka je DOKONČENÁ..."),
        COMPLETED("Dokončená", null);

        private final String displayName;
        private final String nextOrderText;

        OrderState(String displayName, String nextOrderText) {
            this.displayName = displayName;
            this.nextOrderText = nextOrderText;
        }

        public String getDisplayName() {
            return displayName;
        }

        public String getNextOrderText() {
            return nextOrderText;
        }
    }

}
