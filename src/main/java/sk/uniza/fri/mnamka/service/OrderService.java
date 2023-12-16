package sk.uniza.fri.mnamka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.uniza.fri.mnamka.model.Order;
import sk.uniza.fri.mnamka.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired private OrderRepository orderRepository;

    public Order createOrder(Order toBeCreated) {
        toBeCreated = orderRepository.save(toBeCreated);
        orderRepository.flush();
        return toBeCreated;
    }

}
