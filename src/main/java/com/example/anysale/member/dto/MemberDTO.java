package com.example.anysale.member.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private String id;

    private String password;

    private String name;

    private String email;

    private String phone;

    private String role;

    private double score;

    private String profilePhotoUrl;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;


}

