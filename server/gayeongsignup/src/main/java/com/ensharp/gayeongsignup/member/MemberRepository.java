package com.ensharp.gayeongsignup.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
    //findById는 이미 존재.
     /// Object사용 : null값 반환해도 ㄱㅊ음.
    public Member findByEmailAndPassword(String email,String password);

    public boolean existsByEmail(String email);

    Member findByEmail(String email);
}