package com.ensharp.gayeongsignup.member;

import com.ensharp.gayeongsignup.exception.CustomException;
import com.ensharp.gayeongsignup.exception.ErrorCode;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    //@Transactional //조회만 할 경우 필요하지 않고, 삽입할 때에 필요?
    public SignupRequestDto join(SignupRequestDto signupRequestDto) {
        if (memberRepository.existsByEmail(signupRequestDto.email())) {
            System.out.println("이미 있는 이메일 ->success"); //status:409
            //return "이미 사용 중인 이메일입니다.";
            throw new CustomException(ErrorCode.HAS_EMAIL);
        }
        Member member = new Member.MemberBuilder(signupRequestDto).build();
        memberRepository.save(member);
        System.out.println("회원가입 정보 저장");
        return signupRequestDto;
    }

    @Override
    @Transactional
    public String login(String email, String password, HttpSession session) {
        Member foundMember = memberRepository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new CustomException(ErrorCode.INVALID_PASSWORD));

        if (!foundMember.getPassword().equals(password)) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }

        session.setAttribute("LoginUser", foundMember.getUsername()); //세션에 저장
        System.out.println("로그인 성공!");
        return foundMember.getUsername();
        //return "비밀번호가 옳지 않습니다.";
    }

    //비밀번호 변경 시에 성공/실패 점검 및 반환
    @Transactional
    @Override
    public String changePassword(String email, String newPassword) {
        Member foundMember = memberRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));//"회원 못찾음"

        //비밀번호 유효성검사도 프론트에 되어있지만 추가해야함?

        foundMember.updatePassword(newPassword); //데베도 수정됨
        System.out.println("비밀번호 변경 완료");
        return "success";
    }

    @Override
    public SignupRequestDto getUserInfo(String email) {
        Member foundMember = memberRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        System.out.println("회원 정보 전달");
        return foundMember.toDto();
    }
}
