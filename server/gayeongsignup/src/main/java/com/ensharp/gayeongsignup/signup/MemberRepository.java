package com.ensharp.gayeongsignup.signup;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
    public Member findByUsernameAndPassword(String username, String password);

//    @Modifying
//    @Transactional
//    @Query("update Member m set m.memo=memo where m.username=:username")
//    public int saveMemoByUsername(String memo, String username);

}