package spring.example.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.example.spring.entity.Member;

@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
public class HomeController {
    @GetMapping("/login")
    public String showViewLogin(Model model) {
        Member member = new Member();
        model.addAttribute("member", member);
        return "login";
    }
    @GetMapping("/home")
    public String showCustomerHomeView() {
        System.out.println("Im here");
        return "customer-home-view";
    }
}
