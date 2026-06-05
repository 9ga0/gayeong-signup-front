package com.ensharp.gayeongsignup.emailsend;

import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service //빈으로 등록 안해도됨.
public interface MailService {

    String sendTextEmail(String email) throws UnsupportedEncodingException;

    String confirmVerificationCode(String email, String verificationCode);

    String checkEmail(String email);
}
