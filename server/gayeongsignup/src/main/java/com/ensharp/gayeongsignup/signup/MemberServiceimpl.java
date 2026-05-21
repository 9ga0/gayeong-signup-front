package com.ensharp.gayeongsignup.signup;

import com.ensharp.gayeongsignup.emailsend.MailSendService;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceimpl implements MemberService {

    private final MemberRepository memberRepository;


    public MemberServiceimpl(MemberRepository memberRepository, MailSendService mailSendService) {
        this.memberRepository = memberRepository;
    }

    @Override
    public String join(MemberDTO memberDTO) {
        Member member = new Member.MemberBuilder(memberDTO).build();
        member = memberRepository.save(member);
        MemberDTO foundMember = memberRepository.findById(member.getEmail()).get().toDto();
        return "success";
    }


}
