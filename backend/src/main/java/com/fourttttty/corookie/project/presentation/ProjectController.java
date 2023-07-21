package com.fourttttty.corookie.project.presentation;

import com.fourttttty.corookie.project.application.service.MemberService;
import com.fourttttty.corookie.project.response.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponse> memberDetails(@PathVariable Long memberId) {
        return ResponseEntity.ok(new MemberResponse(memberService.findById(memberId).getName()));
    }
}
