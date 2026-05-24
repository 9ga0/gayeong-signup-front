package com.ensharp.gayeongsignup.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
    //findById는 이미 존재.

    public Member findByEmailAndPassword(String email,String password);

    public boolean existsByEmail(String email);

    Member findByEmail(String email);
}