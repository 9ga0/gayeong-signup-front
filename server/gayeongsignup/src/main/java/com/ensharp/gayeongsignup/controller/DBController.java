package com.ensharp.gayeongsignup.controller;

import com.ensharp.gayeongsignup.entity.Member;
import com.ensharp.gayeongsignup.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DBController {
    //MemberRepository repository;
//
//    @PostMapping("/joinProcess")
//    public String joinProcess(Member joinMem){
//        repository.save(joinMem);
//        return "redirect:/main";
//  }
//    @PostMapping("/loginProcess")
//    public String loginProcess(Member loginMem, HttpSession session){
//        Member result = repository.findbyEmailAndPassword (loginMem.getEmail(), loginMem.getPassword());
//        if(result != null){
//            session.setAttribute("loginMember",result);
//            return "redirect:/main";
//        }
//        return "redirect:/login?error=true";
//    }
}
