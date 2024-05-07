package com.ecomrse.projectsw.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecomrse.projectsw.Models.order;
import com.ecomrse.projectsw.Repository.orderRepository;

@Service
public class orderService {

    private final orderRepository OrderRepository;

    @Autowired
    public orderService(orderRepository OrderRepository) {
        this.OrderRepository = OrderRepository;
    }

    public List<order> getAllOrders() {
        return OrderRepository.findAll();
    }

    public ResponseEntity<String> createOrder(order newOrder) {
        if (newOrder.getProduct() == null) {
            throw new IllegalArgumentException("Order must have a product");
        }
        if (newOrder.getQuantity() <= 0) {
            throw new IllegalArgumentException("Order amount must be greater than zero");
        }
        if (newOrder.getItemPrice() == null || newOrder.getItemPrice() <= 0) {
            throw new IllegalArgumentException("Order total amount must be provided and greater than zero");
        }

        newOrder.setTotalPrice(newOrder.getItemPrice() * newOrder.getQuantity());

        OrderRepository.save(newOrder);
        return ResponseEntity.ok("product Updated successfully");
    }

    public void deleteOrder(Long id) {
        OrderRepository.deleteById(id);
    }

}

