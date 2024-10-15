package com.example.anysale.member.service;

import com.example.anysale.member.entity.Member;
import com.example.anysale.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    // 모든 회원 조회
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    // 특정 회원 조회
    public Optional<Member> getMemberById(String id) {
        return memberRepository.findById(id);
    }

    // 회원 저장
    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    // 회원 삭제
    public void deleteMember(String id) {
        memberRepository.deleteById(id);
    }

    // 회원 정보 업데이트
    public Member updateMember(String id, Member updatedMember) {
        Optional<Member> memberOptional = memberRepository.findById(id);
        if (memberOptional.isPresent()) {
            Member existingMember = memberOptional.get();
            // 필드 업데이트 (예: 이름, 이메일 등)
            existingMember.setName(updatedMember.getName());
            existingMember.setEmail(updatedMember.getEmail());
            existingMember.setPhone(updatedMember.getPhone());
            existingMember.setRole(updatedMember.getRole());
            existingMember.setScore(updatedMember.getScore());
            return memberRepository.save(existingMember);
        } else {
            throw new RuntimeException("해당 회원을 찾을 수 없습니다.");
        }
    }

}
