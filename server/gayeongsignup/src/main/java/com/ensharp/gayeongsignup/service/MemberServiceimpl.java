package com.ensharp.gayeongsignup.service;

import com.ensharp.gayeongsignup.dto.MemberDTO;
import com.ensharp.gayeongsignup.entity.Member;
import com.ensharp.gayeongsignup.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceimpl implements MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceimpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public String join(MemberDTO memberDTO) {
        Member member=new Member.MemberBuilder(memberDTO).build();
        member= memberRepository.save(member);
        MemberDTO foundMember = memberRepository.findById(member.getEmail()).get().toDto();

        return "success";
    }

}
