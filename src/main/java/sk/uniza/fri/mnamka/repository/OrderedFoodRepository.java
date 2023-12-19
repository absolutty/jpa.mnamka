package sk.uniza.fri.mnamka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.uniza.fri.mnamka.model.OrderedFood;

import java.util.List;

public interface OrderedFoodRepository extends JpaRepository<OrderedFood, Long> {

    List<OrderedFood> getAllBy();

}
