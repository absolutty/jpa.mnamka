package sk.uniza.fri.mnamka.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import sk.uniza.fri.mnamka.exception.EntityException;
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

    public void updateExistingFoodType(FoodTypeModel foodType) {
        if (foodType == null || foodType.anyRequiredFieldIsEmpty()) {
            throw new EntityException.IsNotValid();
        } else {
            FoodTypeModel foodTypeAlreadyInDatabase = foodTypeRepository.findFoodTypeByName(foodType.getName());
            if (foodTypeAlreadyInDatabase == null) {
                foodTypeRepository.updateFoodTypeWithId(foodType.getId(), foodType.getName());
            } else {
                throw new BadCredentialsException("Name to identify foodtype already exists!");
            }
        }
    }

    public void deleteExistingFoodType(FoodTypeModel foodType) {
        if (foodType == null) {
            throw new EntityException.IsNotValid();
        } else {
            foodTypeRepository.deleteFoodTypeWithIdAndName(foodType.getId(), foodType.getName());
        }
    }

    public void createFoodType(FoodTypeModel foodType) {
        if (foodType == null || foodType.anyRequiredFieldIsEmpty()) {
            throw new EntityException.IsNotValid();
        }

        FoodTypeModel foodTypeAlreadyInDatabase = foodTypeRepository.findFoodTypeByName(foodType.getName());
        if (foodTypeAlreadyInDatabase == null) {
            foodTypeRepository.save(foodType);
            foodTypeRepository.flush();
        } else {
            throw new BadCredentialsException("Name to identify foodtype already exists!");
        }
    }

}
