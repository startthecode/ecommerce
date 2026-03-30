package org.authetication.ecommerce.controllers;

import org.authetication.ecommerce.dto.response.ApiBaseResponse;
import org.authetication.ecommerce.services.order.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {
OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiBaseResponse<String>> placeOrder(){
        orderService.placeOrder();
        return ResponseEntity.ok(new ApiBaseResponse<>(true,"order has been successfully placed","yupppp"));
    }

    @PostMapping("/cancel/{orderid}/order")
    public ResponseEntity<ApiBaseResponse<String>> placeOrder(@PathVariable Long orderid){
        orderService.cancelOrder(orderid);
        return ResponseEntity.ok(new ApiBaseResponse<>(true,"order has been Canceled","So Sad....."));
    }
    
}
