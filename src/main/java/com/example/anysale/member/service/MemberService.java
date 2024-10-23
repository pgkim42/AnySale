package com.example.anysale.member.service;

import com.example.anysale.member.dto.MemberDTO;
import com.example.anysale.member.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    // 회원가입
    Member registerMember(MemberDTO memberDTO);

    // 회원 정보 수정
    void modifyMember(MemberDTO memberDTO);

    // 회원 삭제
    void deleteMember(String id);

    // 회원 단일 검색
    Optional<Member> getMemberById(String id);

    // 회원 정보 반환(마이페이지)
    MemberDTO memberInfo(String id);

    // 회원 목록 가져오기
    List<MemberDTO> getList();

    // 아이디 찾기
    Optional<String> searchById(String name, String email);

    // 비밀번호 찾기
    Optional<String> searchByPw(String id, String name, String email);


    // 엔티티를 DTO로 변환
    default MemberDTO entityToDto(Member entity) {
        return MemberDTO.builder()
                .id(entity.getId())
                .password(entity.getPassword())
                .name(entity.getName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .role(entity.getRole())
                .score(entity.getScore())
                .profilePhotoUrl(entity.getProfilePhotoUrl())
                .createDate(entity.getCreateDate())
                .updateDate(entity.getUpdateDate())
                .build();
    }




//    List<Member> getAllMembers();
//
//    Optional<Member> getMemberById(String id);
//
//    Member saveMember(Member member);
//
//    void deleteMember(String id);
//
//    Member updateMember(String id, Member updatedMember);
//
//    boolean login(String id, String password);
}

