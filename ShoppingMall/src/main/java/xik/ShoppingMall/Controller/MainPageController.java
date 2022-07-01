package xik.ShoppingMall.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainPageController {

    @RequestMapping("/5xik")
    public String MainPage() {
        return "Main/5xik";
    }
}
