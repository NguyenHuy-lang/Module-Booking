package spring.example.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring.example.spring.entity.Customer;
import spring.example.spring.entity.Member;
import spring.example.spring.service.CustomerService;
import spring.example.spring.service.MemberService;

import java.util.List;

@RestController
@RequestMapping("/api/v2/member/")
@RequiredArgsConstructor
public class MemberController {
    private final CustomerService customerService;
    private final MemberService memberService;
    @GetMapping("/all")
    public List<Customer> getListMember() {
        List<Customer> listCustomer = customerService.getAllCustomer();
        return listCustomer;
    }
    @PostMapping("/save-member")
    public String saveMember(@RequestBody Member member) {
        System.out.println(member.toString());
        memberService.saveMember(member);
        return "done save member";
    }

}
