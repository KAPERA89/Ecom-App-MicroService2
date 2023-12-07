package com.sid.orderservice.web;

import com.sid.orderservice.entities.Order;
import com.sid.orderservice.model.Customer;
import com.sid.orderservice.model.Product;
import com.sid.orderservice.repositories.OrderRepository;
import com.sid.orderservice.repositories.ProductItemRepository;
import com.sid.orderservice.services.CustomerRestClientService;
import com.sid.orderservice.services.InventoryRestClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class OrderRestController {
    private OrderRepository orderRepository;
    private ProductItemRepository productItemRepository;
    private CustomerRestClientService customerRestClientService;
    private InventoryRestClientService inventoryRestClientService;

    @GetMapping("/fullOrder/{id}")
    public Order getOrder(@PathVariable Long id){
        Order order =orderRepository.findById(id).get();
        Customer customer = customerRestClientService.customerById(order.getCustomerId());
        order.setCustomer(customer);
        order.getProductItemList().forEach(p -> p.setProduct(inventoryRestClientService.productById(p.getId())));
        return order;
    }
}
