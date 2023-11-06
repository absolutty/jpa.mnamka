package sk.uniza.fri.mnamka.service;

import org.springframework.stereotype.Service;
import sk.uniza.fri.mnamka.repository.FoodTypeRepository;

@Service
public class FoodTypeService {

    private final FoodTypeRepository userRepository;

    public FoodTypeService(FoodTypeRepository userRepository) {
        this.userRepository = userRepository;
    }
}
