package org.authetication.ecommerce.services.order;

import jakarta.transaction.Transactional;
import org.authetication.ecommerce.entity.cart.CartEntity;
import org.authetication.ecommerce.entity.cart.CartItemsEntity;
import org.authetication.ecommerce.entity.order.OrderEntity;
import org.authetication.ecommerce.entity.order.OrderItemsEntity;
import org.authetication.ecommerce.entity.user.UserEntity;
import org.authetication.ecommerce.enums.OrderStatus;
import org.authetication.ecommerce.exception.GenericException;
import org.authetication.ecommerce.repository.order.OrderItemRepository;
import org.authetication.ecommerce.repository.order.OrderRepository;
import org.authetication.ecommerce.repository.payment.PaymentRepository;
import org.authetication.ecommerce.services.UserService;
import org.authetication.ecommerce.services.cart.CartService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {

    OrderRepository orderRepository;
    OrderItemRepository orderItemRepository;
    PaymentRepository paymentRepository;

    // Services External
    UserService userService;
    CartService cartService;

 

    public OrderService(
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository,
            PaymentRepository paymentRepository,
            UserService userService,
           CartService cartService
        ) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.paymentRepository = paymentRepository;
        this.userService = userService;
        this.cartService = cartService;
    }

    @Transactional
    public String placeOrder(){
        UserEntity user = userService.getCurrentUser();
        CartEntity userCart = cartService.getUserCart(user);
        if(userCart.getCartItems().isEmpty()) {
            throw new GenericException("Cart is empty");
        }
        Set<CartItemsEntity> cartItems = userCart.getCartItems();
        Double total =  cartItems.stream()
                .map(CartItemsEntity::getPriceSnapShop)
                .reduce(0.0, Double::sum);
        OrderEntity order = new OrderEntity();

        // Order
        order.setUser(user);
        order.setTotalAmount(total);
        order.setAddress(user.getUserdetail().getAddress());
        order.setOrderItems(cartItems.stream().map((e)->{
           OrderItemsEntity item = new OrderItemsEntity();
           item.setProduct(e.getProduct());
           item.setQuantity(e.getQuantity());
           item.setOrder(order);
           item.setPriceSnapShot(e.getPriceSnapShop());
           return  item;
        }).collect(Collectors.toSet()));

        orderRepository.save(order);
        user.setCart(null);
        userCart.setUser(null);
        cartService.removeCart(userCart);
        return "Order has been created";
    }

    @Transactional
    public String cancelOrder(Long orderID){
        OrderEntity order = orderRepository.findByOrderId(orderID).orElseThrow(()-> new GenericException("There is not order with this id"));
        order.setOrderStatus(OrderStatus.CANCELLED);
        return  "Order Has been canceled";
    }

}
