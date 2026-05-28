package com.ensharp.gayeongsignup.member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class loginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException{
        Member user = (Member) authentication.getPrincipal();
        String sAuth = user.getUser_role();
        String targetUrl = "/";
        if(sAuth.equals("ROLE_USER")){
            targetUrl = "/comom/main.do";
        } else if(sAuth.equals("ROLE_ADMIN")){
            targetUrl="/admin/main.do";
        }

        response.sendRedirect(targetUrl); //
    }
}
