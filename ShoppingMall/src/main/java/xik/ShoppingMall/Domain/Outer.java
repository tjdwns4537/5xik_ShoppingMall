package xik.ShoppingMall.Domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Outer extends Item{

    public Outer(){

    }

    @Getter
    @Setter
    @Column(name="JUMPER")
    private String jumper;

    @Getter
    @Setter
    @Column(name="GARDIGAN")
    private String gardigan;
}
