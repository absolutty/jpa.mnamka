package sk.uniza.fri.mnamka.service;

import org.springframework.stereotype.Service;
import sk.uniza.fri.mnamka.exception.FoodException;
import sk.uniza.fri.mnamka.model.FoodModel;
import sk.uniza.fri.mnamka.model.FoodTypeModel;
import sk.uniza.fri.mnamka.repository.FoodRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {

    private final FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public FoodModel getFood(int id) {
        Optional<FoodModel> food = foodRepository.findById(id);

        if (food.isPresent()) {
            return  food.get();
        } else {
            throw new FoodException.IdNotFoundException(id);
        }
    }

    public List<FoodModel> getFoodsOfType(FoodTypeModel type) {
        return foodRepository.findAllByType(type);
    }

}
