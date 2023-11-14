package sk.uniza.fri.mnamka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.uniza.fri.mnamka.model.FoodModel;
import sk.uniza.fri.mnamka.model.FoodTypeModel;

import java.util.List;

public interface FoodRepository extends JpaRepository<FoodModel, Integer> {

    List<FoodModel> findAllByType(FoodTypeModel type);

}
