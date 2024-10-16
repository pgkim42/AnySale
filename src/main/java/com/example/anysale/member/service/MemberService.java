package com.example.anysale.member.service;

import com.example.anysale.member.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    List<Member> getAllMembers();

    Optional<Member> getMemberById(String id);

    Member saveMember(Member member);

    void deleteMember(String id);

    Member updateMember(String id, Member updatedMember);

    boolean login(String id, String password);
}

