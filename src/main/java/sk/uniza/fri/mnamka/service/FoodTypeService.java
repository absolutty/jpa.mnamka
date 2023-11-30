package sk.uniza.fri.mnamka.service;

import org.springframework.stereotype.Service;
import sk.uniza.fri.mnamka.model.FoodTypeModel;
import sk.uniza.fri.mnamka.repository.FoodTypeRepository;

import java.util.List;

@Service
public class FoodTypeService {

    private final FoodTypeRepository foodTypeRepository;

    public FoodTypeService(FoodTypeRepository foodTypeRepository) {
        this.foodTypeRepository = foodTypeRepository;
    }

    public List<FoodTypeModel> getAllFoodTypes() {
        return foodTypeRepository.findAllBy();
    }

    public List<String> getFoodTypesIdentifiers() {
        return foodTypeRepository.foodTypesNames();
    }

    public FoodTypeModel getFoodTypeByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Given NAME is null!");
        } else {
            return foodTypeRepository.findFoodTypeByName(name);
        }
    }

    public void updateExistingUser(FoodTypeModel foodType) {
        if (foodType == null || foodType.anyRequiredFieldIsEmpty()) {
            throw new IllegalArgumentException("Given USER is not correct!");
        } else {
            foodTypeRepository.updateFoodTypeWithId(foodType.getFood_type_id(), foodType.getName());
        }
    }

    public void deleteExistingFoodType(FoodTypeModel foodType) {
        if (foodType == null) {
            throw new IllegalArgumentException("Given FOODTYPE null!");
        } else {
            foodTypeRepository.deleteFoodTypeWithIdAndName(foodType.getFood_type_id(), foodType.getName());
        }
    }

}
