package sk.uniza.fri.mnamka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.uniza.fri.mnamka.model.Order;
import sk.uniza.fri.mnamka.model.OrderedFood;
import sk.uniza.fri.mnamka.repository.OrderRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    @Autowired private OrderRepository orderRepository;

    public Order createOrder(Order toBeCreated) {
        toBeCreated = orderRepository.save(toBeCreated);
        orderRepository.flush();
        return toBeCreated;
    }

    public List<Order> getAllOrders() {
        return orderRepository.getAllBy();
    }

    public static Map<Order, List<OrderedFood>> groupByOrder(List<Order> orders, List<OrderedFood> orderedFoods) {
        Map<Order, List<OrderedFood>> grouppedOrderedFoods = new LinkedHashMap<>();

        for (Order type : orders) {
            for (OrderedFood food : orderedFoods) {
                if (food.getOrder().equals(type)) {
                    if (grouppedOrderedFoods.containsKey(type)) {
                        grouppedOrderedFoods.get(type).add(food);
                    } else {
                        List<OrderedFood> arrFoodsValue = new ArrayList<>();
                        arrFoodsValue.add(food);
                        grouppedOrderedFoods.put(type, arrFoodsValue);
                    }
                }
            }
        }

        return grouppedOrderedFoods;
    }

}
