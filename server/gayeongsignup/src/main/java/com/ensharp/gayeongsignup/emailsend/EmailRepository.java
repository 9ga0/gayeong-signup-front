package com.ensharp.gayeongsignup.emailsend;

import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailRepository extends JpaRepository<EmailVarifyEntity,String> {
    public Optional<Email> findByEmail(String email);
    public boolean existsByEmail(String email);
    public void deleteByEmail(String email);
}
