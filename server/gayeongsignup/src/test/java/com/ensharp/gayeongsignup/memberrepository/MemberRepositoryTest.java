package com.ensharp.gayeongsignup.memberrepository;

import com.ensharp.gayeongsignup.dto.MemberDTO;
import com.ensharp.gayeongsignup.entity.Member;
import com.ensharp.gayeongsignup.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.Principal;

@SpringBootTest
public class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository; //repository자체에 메소드들 많다.
//
//    @Test
//    public void crudTest() {
//        MemberDTO memberDTO = new MemberDTO("koo@naver.com","1234","구가","000","999");
//
//        //create test
//        //memberRepository.save(member);
//
//        //get test
//        //Member foundMember = memberRepository.findById(1L).get();
//    }


    /// ////
    @DisplayName("회원가입_된다")
    @Test
    public void crudTest() {
        //given
        MemberDTO memberDTO = new MemberDTO("koo@naver.com","1234","구가","000","999");
        //Principal principal = () -> "member1@test.com";

        //when
        //create test
        //memberRepository.save();

        //get test
        //MemberDTO foundMember = memberRepository.findById(memberDTO.email()).get().toDto();

    }
}
