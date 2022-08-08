package xik.ShoppingMall.Domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
public class Address {


    @Getter @Setter
    String city;
    @Getter @Setter
    String street;
    @Getter @Setter
    String zipcode;

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    public Address() {

    }
}
