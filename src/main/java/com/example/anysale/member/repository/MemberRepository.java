package com.example.anysale.member.repository;

import com.example.anysale.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {

    @Modifying
    @Query("DELETE FROM Member m WHERE m.id = :id")
    void deleteById(@Param("id") String id);

    @Query("select m from Member m where m.id = :id")
    Optional<Member> selectById(@Param("id") String id);


}
