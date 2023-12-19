package sk.uniza.fri.mnamka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import sk.uniza.fri.mnamka.model.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> getAllByOrderById();

    @Modifying
    @Transactional
    @Query("UPDATE Order o SET o.orderState = :newOrderState WHERE o.id = :orderId")
    void updateOrderStateById(@Param("orderId") Long orderId, @Param("newOrderState") Order.OrderState newOrderState);

}
