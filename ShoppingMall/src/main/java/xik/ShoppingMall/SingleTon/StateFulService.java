package xik.ShoppingMall.SingleTon;

public class StateFulService {

    public int order(String name, int _price) {
        System.out.println("name:" + name + " price:" + _price);
        int price =  _price;
        return price;
    }
}
