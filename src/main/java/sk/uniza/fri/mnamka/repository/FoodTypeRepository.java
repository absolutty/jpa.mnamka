package sk.uniza.fri.mnamka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.uniza.fri.mnamka.model.FoodTypeModel;

import java.util.List;

public interface FoodTypeRepository extends JpaRepository<FoodTypeModel, Integer> {

    List<FoodTypeModel> findAllBy();

}
