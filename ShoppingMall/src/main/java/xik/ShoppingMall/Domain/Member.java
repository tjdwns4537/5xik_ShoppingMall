package xik.ShoppingMall.Domain;

import lombok.Getter;
import lombok.Setter;

public class Member {

    @Getter
    @Setter
    private Long id; // 시스템에 저장하기 위해 시스템이 정하는 변수

    @Getter
    @Setter
    private String name; // 고객이 입력한 데이터

    @Getter
    @Setter
    private String phoneNumber; // 휴대폰 번호

}
