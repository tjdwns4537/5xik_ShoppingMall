package xik.ShoppingMall.Service;

import xik.ShoppingMall.Order.Order;

public interface OrderService {
    Order createOrder(Long MemberId, String itemName, int itemPrice);
}
