package sk.uniza.fri.mnamka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.uniza.fri.mnamka.model.FoodModel;

public interface FoodRepository extends JpaRepository<FoodModel, Integer> {

}