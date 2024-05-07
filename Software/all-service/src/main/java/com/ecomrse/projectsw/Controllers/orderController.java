package com.ecomrse.projectsw.Controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecomrse.projectsw.Aspect.RequiredRole;
import com.ecomrse.projectsw.Models.order;
import com.ecomrse.projectsw.Repository.ProductRepository;
import com.ecomrse.projectsw.Services.ProductService;
import com.ecomrse.projectsw.Services.orderService;

@RestController
@RequestMapping("/orders")
public class orderController {

    @Autowired
    private orderService OrderService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    @CrossOrigin(origins = "*")
    @GetMapping("/get-allorders")
    @RequiredRole("ADMIN")
    public ResponseEntity<List<order>> getAllOrders(@RequestParam("token") String token) {
        List<order> orders = OrderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/createorder")
    @RequiredRole("USER")
    public ResponseEntity<String> createOrder(@RequestBody Map<String, String> request, @RequestParam("token") String token) {
        order order = new order();
        // order.setProduct();
        order.setProduct(productRepository.getById(Long.parseLong(request.get("product_id"))));
        order.setItemPrice(Double.parseDouble(request.get("price")));
        order.setQuantity(Long.parseLong(request.get("quantity")));
        order.setTotalPrice(Double.parseDouble(request.get("total")));
        productService.updateAmount(Long.parseLong(request.get("product_id")), request.get("quantity"));
        return OrderService.createOrder(order);

    }

    @DeleteMapping("/{id}")
    @RequiredRole("ADMIN")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        OrderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
