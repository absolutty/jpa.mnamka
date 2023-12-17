package sk.uniza.fri.mnamka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.uniza.fri.mnamka.model.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> getAllBy();

}
