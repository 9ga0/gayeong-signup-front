package com.ensharp.gayeongsignup.emailsend;

import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public interface MailService {

    String sendTxtEmail(String email) throws UnsupportedEncodingException;

    String confirmVerificationCode(String email, String verificationCode);

    String checkEmail(String email);
}
