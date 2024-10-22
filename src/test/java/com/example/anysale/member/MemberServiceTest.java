package com.example.anysale.member;

import com.example.anysale.member.dto.MemberDTO;
import com.example.anysale.member.entity.Member;
import com.example.anysale.member.repository.MemberRepository;
import com.example.anysale.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    MemberDTO memberDTO;

    public MemberServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveMember() {
        Member member = Member.builder()
                .id("testUser")
                .password("password")
                .name("Test User")
                .email("test@example.com")
                .phone("01012345678")
                .role("ROLE_USER")
                .score(5.0)
                .build();

        when(memberRepository.save(member)).thenReturn(member);

        Member savedMember = memberService.registerMember(memberDTO);

        assertNotNull(savedMember);
        assertEquals("testUser", savedMember.getId());
    }

    @Test
    public void testGetMemberById() {
        Member member = Member.builder()
                .id("testUser")
                .name("Test User")
                .email("test@example.com")
                .phone("01012345678")
                .role("ROLE_USER")
                .score(5.0)
                .build();

        when(memberRepository.findById("testUser")).thenReturn(Optional.of(member));

        Optional<Member> foundMember = memberService.getMemberById("testUser");

        assertTrue(foundMember.isPresent());
        assertEquals("testUser", foundMember.get().getId());
    }

    @Test
    public void testDeleteMember() {
        doNothing().when(memberRepository).deleteById("testUser");
        memberService.deleteMember("testUser");
        verify(memberRepository, times(1)).deleteById("testUser");
    }
}
