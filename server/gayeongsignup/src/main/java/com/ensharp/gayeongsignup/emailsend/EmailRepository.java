package com.ensharp.gayeongsignup.emailsend;

import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface EmailRepository extends JpaRepository<EmailVarifyEntity,String> {
    public EmailVarifyEntity findByEmail(String email);
    public boolean existsByEmail(String email);

    @Modifying
    public void deleteByEmail(String email);
}
