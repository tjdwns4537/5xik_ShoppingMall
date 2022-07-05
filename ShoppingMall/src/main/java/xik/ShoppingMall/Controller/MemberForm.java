package xik.ShoppingMall.Controller;


import lombok.Getter;
import lombok.Setter;

public class MemberForm {

    // memberNew 페이지의 form 태그와 매칭이 되면서 값이 들어온다.
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String phoneNumber;

}
