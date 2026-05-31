package com.ensharp.gayeongsignup.emailsend;

import com.ensharp.gayeongsignup.exception.CustomException;
import com.ensharp.gayeongsignup.exception.ErrorCode;
import com.ensharp.gayeongsignup.member.MemberRepository;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
public class MailServiceImpl implements MailService {
    private final JavaMailSender mailSender;
    private String authNumber;
    private final EmailRepository emailRepository;
    private final MemberRepository memberRepository;

    public MailServiceImpl(JavaMailSender mailSender, EmailRepository emailRepository, MemberRepository memberRepository) {
        this.emailRepository = emailRepository;
        this.mailSender = mailSender;
        this.memberRepository = memberRepository;
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

    @Transactional
    public void deleteEmailSendHistoryIfExists(String email) {
        if (emailRepository.existsByEmail(email)) {
            emailRepository.deleteByEmail(email);
            System.out.println("이메일 삭제 완료");
        }
    }
    @Transactional
    public void saveSendEmail(String email, String authNumber) {
        EmailVerification emailEntity = new EmailVerification(email, authNumber);
        System.out.println(emailEntity.getIsAuthenticated());
        emailRepository.save(emailEntity);
        System.out.println("이메일과 인증번호를 데이터베이스에도 저장");
    }

    @Override
    public String sendTextEmail(String email) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");

            helper.setFrom(System.getenv("SERVER_EMAIL"));
            helper.setTo(email);
            helper.setSubject("En# SignUp 인증 번호");
            makeRandomNumber();
            helper.setText("이메일 인증 코드: " + authNumber);

            mailSender.send(mimeMessage);
            System.out.println("이메일 전송 성공!");
            EmailVerification emailEntity = new EmailVerification(email, authNumber);

            saveSendEmail(email, authNumber);

            return "success";
        } catch (Exception e) {
            System.out.println("500 서버 오류 발생: " + e.getMessage());
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
            //return "fail";
        }
    }

    @Transactional
    @Override
    public String confirmVerificationCode(String email, String verificationCode) {
        EmailVerification emailVerification = emailRepository.findByEmail(email)
                .orElseThrow(()->new CustomException(ErrorCode.MAIL_NOT_FOUND));//"해당 이메일로 보낸적이 없다"

        if (emailVerification.getVerificationCode().equals(verificationCode)) {
            emailVerification.updateIsAuthenticated(true);
            System.out.println("인증번호 일치");
            return "success";
        } else {
            emailVerification.updateIsAuthenticated(false);
            System.out.println("인증번호 불일치");
            throw new CustomException(ErrorCode.INCORRECT_AUTH_NUMBER);
            //return "fail";
        }
    }

    @Override
    public String checkEmail(String email) {
        if (memberRepository.existsByEmail(email)) {
            System.out.println("이미 사용 중인 이메일");
            throw new CustomException(ErrorCode.HAS_EMAIL);
            //return "이미 사용 중인 이메일입니다"; //409
        }
        System.out.println("이메일 중복 검사 통과");
        return "사용 가능한 이메일입니다"; //200
    }
}
