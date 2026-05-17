package com.ensharp.gayeongsignup.repository;

import com.ensharp.gayeongsignup.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}