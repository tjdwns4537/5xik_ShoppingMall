package xik.ShoppingMall.Domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Shirts extends Item{

    @Getter
    @Setter
    private String halfShirts;

    @Getter
    @Setter
    private String longShirts;

}
