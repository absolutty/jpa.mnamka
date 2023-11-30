package sk.uniza.fri.mnamka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import sk.uniza.fri.mnamka.model.FoodModel;
import sk.uniza.fri.mnamka.model.FoodTypeModel;

import java.util.List;

public interface FoodRepository extends JpaRepository<FoodModel, Integer> {

    List<FoodModel> findAllByType(FoodTypeModel type);

    List<FoodModel> findAllBy();

    @Query("SELECT f.name FROM FoodModel f ORDER BY f.id")
    List<String> foodNames();

    @Query("SELECT f FROM FoodModel f WHERE f.name = :name")
    FoodModel findFoodByName(@Param("name") String name);

    FoodModel findFoodModelById(Long id);

    @Modifying
    @Transactional
    @Query( "UPDATE FoodModel f " +
            "SET f.name = :newName, " +
            "f.description = :newDescription, " +
            "f.measurement = :newMeasurement, " +
            "f.price = :newPrice, " +
            "f.type = :newFoodType, " +
            "f.img_url = :newImgUrl " +
            "WHERE f.id = :foodId")
    void updateFoodWithId(
            @Param("foodId") Long foodId,
            @Param("newName") String newName,
            @Param("newDescription") String newDescription,
            @Param("newMeasurement") String newMeasurement,
            @Param("newPrice") double newPrice,
            @Param("newFoodType") FoodTypeModel newFoodType,
            @Param("newImgUrl") String newImgUrl
    );

    @Modifying
    @Transactional
    @Query("DELETE FROM FoodModel f WHERE f.id = :foodId")
    void deleteUserWithIdAndEmail(@Param("foodId") Long foodId);

}
