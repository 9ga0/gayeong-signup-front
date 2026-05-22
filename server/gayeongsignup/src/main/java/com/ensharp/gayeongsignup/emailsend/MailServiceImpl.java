package com.ensharp.gayeongsignup.emailsend;

import com.ensharp.gayeongsignup.signup.Member;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Random;

@Service
public class MailServiceImpl implements MailService {
    private final JavaMailSender mailSender;
    private String authNumber;
    private EmailRepository emailRepository;

    public MailServiceImpl(JavaMailSender mailSender, EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
        this.mailSender = mailSender;
    }

    //6자리 양수 랜덤 반환
    private void makeRandomNumber() {
        Random r = new Random();
        StringBuffer str = new StringBuffer(""); //StringBuilder에 비해 동기화 지원? 멀티 스레드에 적합
        for (int i = 0; i < 6; i++) {
            str.append(r.nextInt(0, 10));
        }
        authNumber = str.toString();
    }

    @Override
    public String sendTxtEmail(String email) throws UnsupportedEncodingException {
        try {
            if (emailRepository.existsByEmail(email)) { //이미 이메일있으면 그 데이터 지우고 새로 넣기위함.
                emailRepository.deleteByEmail(email);
            }
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");

            helper.setFrom(System.getenv("SERVER_EMAIL"));
            helper.setTo(email);
            helper.setSubject("En# SignUp 인증 번호");
            makeRandomNumber();
            helper.setText("이메일 인증 코드: " + authNumber);

            mailSender.send(mimeMessage);
            System.out.println("이메일 전송 성공!");
            EmailVarifyEntity emailEntity = new EmailVarifyEntity(email, authNumber);
            emailRepository.save(emailEntity);
            System.out.println("이메일과 인증번호를 데이터베이스에도 저장");
            return "success";
        } catch (Exception e) {
            System.out.println("이메일 전송 중에 오류 발생." + e.getMessage());
            return "fail";
        }
    }

    @Override
    public String confirmVerificationCode(String email, String verificationCode) {
        EmailVarifyEntity emailVarify = emailRepository.findByEmail(email);
        if (emailVarify == null) {
            System.out.println("해당 이메일로 보낸적이 없다");
            return "fail";
        }
        if (emailVarify.getVerificationCode().equals(verificationCode)) {
            System.out.println("인증번호 일치");
            return "success";
        } else {
            System.out.println("인증번호 불일치");
            return "fail";
        }
    }


}
