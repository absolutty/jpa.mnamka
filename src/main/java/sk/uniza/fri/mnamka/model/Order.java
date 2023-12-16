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

        PENDING("Čakajúca"),
        PROCESSING("Spracovávaná"),
        COMPLETED("Dokončená");

        private final String displayName;

        OrderState(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }

    }

}
