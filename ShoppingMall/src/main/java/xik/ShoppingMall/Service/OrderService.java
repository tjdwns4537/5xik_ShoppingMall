package xik.ShoppingMall.Service;

import xik.ShoppingMall.Domain.Order;

public interface OrderService {
    Order createOrder(Long MemberId,Integer price);
}
