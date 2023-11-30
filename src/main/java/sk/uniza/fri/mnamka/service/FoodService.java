package sk.uniza.fri.mnamka.service;

import org.springframework.stereotype.Service;
import sk.uniza.fri.mnamka.model.FoodModel;
import sk.uniza.fri.mnamka.model.FoodTypeModel;
import sk.uniza.fri.mnamka.repository.FoodRepository;

import java.util.*;

@Service
public class FoodService {

    private final FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public static Map<FoodTypeModel, List<FoodModel>> categorizeFoodByFoodTypes(List<FoodModel> foods) {
        Map<FoodTypeModel, List<FoodModel>> categorizedFood = new HashMap<>();

        for (FoodModel food: foods) {
            FoodTypeModel type = food.getType();

            //FoodType uz existuje v Map
            if (categorizedFood.containsKey(type)) {
                categorizedFood.get(type).add(food);
            }
            //FoodType este nie je pridany do Map
            else {
                ArrayList<FoodModel> listOfFoods = new ArrayList<>();
                listOfFoods.add(food);
                categorizedFood.put(type, listOfFoods);
            }
        }

        return categorizedFood;
    }

    public List<FoodModel> getAllFoods() {
        return foodRepository.findAllBy();
    }

    public FoodModel getFoodById(Long id) {
        return foodRepository.findFoodModelById(id);
    }

    public void updateExistingFood(FoodModel food) {
        if (food == null || food.anyRequiredFieldIsEmpty()) {
            throw new IllegalArgumentException("Given FOOD is not correct!");
        } else {
            foodRepository.updateFoodWithId(
                    food.getId(), food.getName(), food.getDescription(), food.getMeasurement(), food.getPrice(), food.getType(), food.getImg_url()
            );
        }
    }

    public void deleteExistingFood(FoodModel food) {
        if (food == null) {
            throw new IllegalArgumentException("Given FOOD null!");
        } else {
            foodRepository.deleteUserWithIdAndEmail(food.getId());
        }
    }

}
