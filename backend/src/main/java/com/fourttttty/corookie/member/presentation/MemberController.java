package com.fourttttty.corookie.member.presentation;

import com.fourttttty.corookie.member.application.service.MemberService;
import com.fourttttty.corookie.member.dto.response.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponse> memberDetails(@PathVariable Long memberId) {
        return ResponseEntity.ok(new MemberResponse(memberService.findEntityById(memberId).getName()));
    }
}
