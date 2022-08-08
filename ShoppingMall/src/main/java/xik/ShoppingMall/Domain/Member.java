package xik.ShoppingMall.Domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Member {

    @Getter
    @Setter
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MEMBER_ID")
    private Long id; // 시스템에 저장하기 위해 시스템이 정하는 변수

    @Getter
    @Setter
    @Column(name = "NAME")
    private String name; // 고객이 입한 데이터

    @Getter
    @Setter
    @Column(name="PHONENUMBER")
    private String phonenumber; // 휴대폰 번호

    @Getter @Setter
    @Embedded
    private Address address;

    @Getter @Setter
    @Embedded
    private WorkPeriod workPeriod;

    public Member(){

    }

}
