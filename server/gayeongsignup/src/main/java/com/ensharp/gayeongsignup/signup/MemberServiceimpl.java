package com.ensharp.gayeongsignup.signup;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceimpl implements MemberService {

    private final MemberRepository memberRepository;


    public MemberServiceimpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    //@Transactional //조회만 할 경우 필요하지 않고, 삽입할 때에 필요?
    public String join(SignupRequestDto signupRequestDto) {
        if(memberRepository.existsByEmail(signupRequestDto.email())){
            System.out.println("이미 있는 이메일 ->success"); //status:409
            return "이미 사용 중인 이메일입니다.";
        }
        Member member = new Member.MemberBuilder(signupRequestDto).build();
        memberRepository.save(member);
        System.out.println("회원가입 정보 저장");
        return "success";
    }

    @Override
    public String login(String email, String password){
        SignupRequestDto foundMember = memberRepository.findByEmailAndPassword(email,password).toDto();
        if (foundMember!=null) {
            System.out.println("로그인 성공!");
            return foundMember.username();
        }
        System.out.println("비밀번호 틀림");
        return "비밀번호가 옳지 않습니다.";
    }

    //회원가입할때 이메일 중복됐는지 체크해서 반환
    public boolean checkLoginIdDuplicate(String email) {
        return memberRepository.existsByEmail(email);
    }


}
