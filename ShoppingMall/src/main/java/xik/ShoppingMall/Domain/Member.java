package xik.ShoppingMall.Domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Member {

    @Getter
    @Setter
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 시스템에 저장하기 위해 시스템이 정하는 변수

    @Getter
    @Setter
    @Column(name="name")
    private String name; // 고객이 입력한 데이터

    @Getter
    @Setter
    @Column(name="phonenumber")
    private String phoneNumber; // 휴대폰 번호

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name="grade")
    private Grade grade;

}
