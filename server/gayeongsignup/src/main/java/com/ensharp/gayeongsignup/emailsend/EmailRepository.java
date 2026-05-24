package com.ensharp.gayeongsignup.emailsend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface EmailRepository extends JpaRepository<EmailVerification,String> {
    public EmailVerification findByEmail(String email); //Optional
    public boolean existsByEmail(String email);

    //@Modifying
    ///어노테이션 잘 공부하기!!!
    public void deleteByEmail(String email);
}
