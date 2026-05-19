package com.ensharp.gayeongsignup.repository;

import com.ensharp.gayeongsignup.dto.MemberDTO;
import com.ensharp.gayeongsignup.entity.Member;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

//@NoRepositoryBean
public interface MemberRepository extends JpaRepository<Member, String> {
    public Member findByUsernameAndPassword(String username, String password);

//    @Modifying
//    @Transactional
//    @Query("update Member m set m.memo=memo where m.username=:username")
//    public int saveMemoByUsername(String memo, String username);

}