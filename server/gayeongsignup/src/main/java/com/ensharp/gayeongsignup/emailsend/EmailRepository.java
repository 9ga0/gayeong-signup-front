package com.ensharp.gayeongsignup.emailsend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface EmailRepository extends JpaRepository<EmailVerification,String> {
    public Optional<EmailVerification> findByEmail(String email); ///Optional 사용하기
    public boolean existsByEmail(String email);

    public void deleteByEmail(String email);
    public void deleteAll();
}
