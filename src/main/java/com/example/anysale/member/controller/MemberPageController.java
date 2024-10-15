package com.example.anysale.member.controller;

import com.example.anysale.member.entity.Member;
import com.example.anysale.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/members")  // 페이지 렌더링을 위한 경로
public class MemberPageController {

    @Autowired
    private MemberService memberService;

    // 회원 목록 페이지 렌더링
    @GetMapping
    public String getAllMembers(Model model) {
        List<Member> members = memberService.getAllMembers();
        model.addAttribute("members", members);
        return "members";  // templates/members.html 파일을 렌더링
    }
}