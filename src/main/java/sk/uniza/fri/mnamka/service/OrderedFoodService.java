package sk.uniza.fri.mnamka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.uniza.fri.mnamka.model.OrderedFood;
import sk.uniza.fri.mnamka.repository.OrderedFoodRepository;

@Service
public class OrderedFoodService {

    @Autowired private OrderedFoodRepository orderedFoodRepository;

    public OrderedFood createOrderFood(OrderedFood toBeCreated) {
        toBeCreated = orderedFoodRepository.save(toBeCreated);
        orderedFoodRepository.flush();
        return toBeCreated;
    }

}
