package com.ensharp.gayeongsignup.emailsend;

import jakarta.mail.internet.MimeUtility;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class MailSendServiceImpl implements MailSendService{
    private final JavaMailSender mailSender;

    public MailSendServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public String sendTxtEmail(MailTxtSendDto mailTxtSendDto) throws UnsupportedEncodingException {
        SimpleMailMessage simpleMailMessage= new SimpleMailMessage();
        simpleMailMessage.setTo(mailTxtSendDto.emailAddr());
        simpleMailMessage.setSubject(mailTxtSendDto.subject());
        simpleMailMessage.setText(mailTxtSendDto.content());

        try{
            mailSender.send(simpleMailMessage);
            System.out.println("이메일 전송 성공!");
            return "success";
        }catch(MailException e) {
            System.out.println("이메일 전송 중에 오류 발생." + e.getMessage());
            return "fail";
        }
    }
}
