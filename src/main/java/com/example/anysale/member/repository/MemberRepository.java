package com.example.anysale.member.repository;

import com.example.anysale.member.dto.MemberDTO;
import com.example.anysale.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {

    @Modifying
    @Query("DELETE FROM Member m WHERE m.id = :id")
    void deleteById(@Param("id") String id);

    @Query("select m from Member m where m.id = :id")
    Optional<Member> selectById(@Param("id") String id);

    @Query("select m.id from Member m where m.name = :name and m.email = :email")
    Optional<String> searchById(@Param("name") String name, @Param("email") String email);

    @Query("select m.password from Member m where m.id = :id and m.name = :name and m.email = :email")
    Optional<String> searchByPw( @Param("id") String id,@Param("name") String name, @Param("email") String email);


}
