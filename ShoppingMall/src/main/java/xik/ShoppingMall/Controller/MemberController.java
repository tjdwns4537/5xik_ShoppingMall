package xik.ShoppingMall.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import xik.ShoppingMall.Domain.Grade;
import xik.ShoppingMall.Domain.Member;
import xik.ShoppingMall.Service.MemberServiceImp;
import xik.ShoppingMall.Service.MemberServiceInterface;

import java.util.List;

@Controller
public class MemberController {

    private MemberServiceInterface memberService;

    @Autowired
    public MemberController(MemberServiceInterface memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/login")
    public String Login() {
        return "/Login/login";
    }

    @GetMapping("/new")
    public String New() {
        return "/Login/signup";
    }

    @PostMapping("/signup")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        member.setPhonenumber(form.getPhoneNumber());

        memberService.join(member);

        // redirect:/ 하면 홈화면으로 보내는 것이다.
        return "redirect:/5xik";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMember();
        model.addAttribute("members", members);
        return "/Login/memberCheck";
    }
}
