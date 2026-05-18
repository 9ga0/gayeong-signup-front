package com.ensharp.gayeongsignup.service;

import com.ensharp.gayeongsignup.dto.JoinRequest;
import com.ensharp.gayeongsignup.entity.Member;
import com.ensharp.gayeongsignup.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceimpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public String join(JoinRequest joinRequest) {
        Member member = Member.builder()
                .email(joinRequest.getEmail())
                .password(joinRequest.getPassword())
                .username(joinRequest.getUsername())
                .street_address(joinRequest.getStreetAddress())
                .detail_address(joinRequest.getDetailAddress())
                .build();
        memberRepository.save(member);

        return "success";
    }

}
