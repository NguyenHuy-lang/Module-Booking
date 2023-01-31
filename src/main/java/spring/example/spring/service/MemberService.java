package spring.example.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.example.spring.entity.Member;
import spring.example.spring.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public Member getMemeberById(Long id) {
        return memberRepository.getReferenceById(id);
    }
    public void deleteMemberById(Long id) {
        memberRepository.deleteById(id);
    }
    public void saveMember(Member member) {
        memberRepository.save(member);
    }
    public List<Member> getAllMember() {
        return memberRepository.findAll();
    }
}
