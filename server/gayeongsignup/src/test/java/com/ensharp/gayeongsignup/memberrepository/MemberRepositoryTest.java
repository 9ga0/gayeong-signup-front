package com.ensharp.gayeongsignup.memberrepository;

import com.ensharp.gayeongsignup.entity.Member;
import com.ensharp.gayeongsignup.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository; //repository자체에 메소드들 많다.

    @Test
    public void crudTest() {
        Member member = Member.builder()
                .email("koo@naver.com")
                .password("1234")
                .username("구가")
                .street_address("000")
                .detail_address("999")
                .build();

        //create test
        memberRepository.save(member);

        //get test
        Member foundMember = memberRepository.findById(1L).get();
    }
}
