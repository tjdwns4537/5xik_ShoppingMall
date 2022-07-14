package xik.ShoppingMall.Domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Member {

    @Getter
    @Setter
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MEMBER_ID")
    private Long id; // 시스템에 저장하기 위해 시스템이 정하는 변수

    @Getter
    @Setter
    @Column(name="NAME")
    private String name; // 고객이 입력한 데이터

    @Getter
    @Setter
    @Column(name="PHONENUMBER")
    private String phoneNumber; // 휴대폰 번호

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name="GRADE")
    private Grade grade;

    @Setter
    @Getter
    @Column(name = "ZIPCODE")
    private String zipcode;

    private LocalDate createDate;
    private LocalDateTime lastModifiedDate;

}
