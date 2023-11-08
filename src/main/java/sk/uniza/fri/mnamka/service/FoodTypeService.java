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

}
