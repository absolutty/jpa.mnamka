package sk.uniza.fri.mnamka.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ORDERS")
public class Order {

    public enum OrderState {
        PENDING, PROCESSING, COMPLETED, CANCELLED;
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CREATION_DATE", nullable = false)
    private Date creationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATE", nullable = false)
    private OrderState state;

    @ManyToOne @JoinColumn(name = "CREATED_BY_USER")
    private User user;

}
