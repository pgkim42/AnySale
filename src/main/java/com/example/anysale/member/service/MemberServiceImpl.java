package com.example.anysale.member.service;

import com.example.anysale.member.entity.Member;
import com.example.anysale.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Optional<Member> getMemberById(String id) {
        return memberRepository.findById(id);
    }

    @Override
    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public void deleteMember(String id) {
        memberRepository.deleteById(id);
    }

    @Override
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

    @Override
    public boolean login(String id, String password) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));

        // 비밀번호 비교 (암호화 없이)
        return member.getPassword().equals(password);
    }
}