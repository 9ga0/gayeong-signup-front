package com.ensharp.gayeongsignup.signup;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/post-api")
@CrossOrigin(origins = "")
public class MemberController {
    private final MemberServiceimpl memberServiceimpl;
    public MemberController(MemberServiceimpl memberServiceimpl) {
        this.memberServiceimpl = memberServiceimpl;
    }

    @PostMapping(value = "/default")
    public String postMethod() {
        return "안녕하세요!";
    }

    @PostMapping("/member")
    public String postMember(@RequestBody @Valid Map<String, String> postData) {
        StringBuilder sb = new StringBuilder();

        postData.entrySet().forEach(map -> {
            sb.append(map.getKey() + " : " + map.getValue() + "\n");
        });
        return sb.toString();
    }
    //위랑 같은 결과?
    @PostMapping(value = "/member2")
    public String postMemberDto(@RequestBody @Valid MemberDTO memberDTO) {
        return memberDTO.toString();
    }

    //회원가입
    @PostMapping("/join")
    public String join(@RequestBody @Valid MemberDTO memberDTO) {
        String result = memberServiceimpl.join(memberDTO);

        if ("success".equalsIgnoreCase(result)) { //nullException발생 방지
            return "회원가입 success";
        } else {
            return "회원가입 fail";
        }
    }

}
/// @exceptionhandler, @controlleradvice 참고해서 추가하기
