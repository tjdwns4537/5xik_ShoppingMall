package xik.ShoppingMall.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    @GetMapping("/login")
    public String Login() {
        return "/Login/login";
    }

    @GetMapping("/new")
    public String New() {
        return "/Login/memberNew";
    }


}
