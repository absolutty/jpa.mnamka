package sk.uniza.fri.mnamka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import sk.uniza.fri.mnamka.model.FoodTypeModel;

import java.util.List;

public interface FoodTypeRepository extends JpaRepository<FoodTypeModel, Integer> {

    @Query("SELECT ft FROM FoodTypeModel ft WHERE ft.name = :name")
    FoodTypeModel findFoodTypeByName(@Param("name") String name);

    List<FoodTypeModel> findAllBy();

    @Query("SELECT ft.name FROM FoodTypeModel ft ORDER BY ft.food_type_id")
    List<String> foodTypesNames();

    @Modifying
    @Transactional
    @Query( "UPDATE FoodTypeModel ft " +
            "SET ft.name = :newName " +
            "WHERE ft.food_type_id = :foodTypeId")
    void updateFoodTypeWithId(
            @Param("foodTypeId") Long foodTypeId,
            @Param("newName") String newName
    );

    @Modifying
    @Transactional
    @Query("DELETE FROM FoodTypeModel ft WHERE ft.food_type_id = :foodTypeId AND ft.name = :name")
    void deleteFoodTypeWithIdAndName(
            @Param("foodTypeId") Long foodTypeId,
            @Param("name") String name
    );

}
