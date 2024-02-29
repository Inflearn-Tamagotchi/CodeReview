package api.employee.controller;

import api.employee.model.MemberForm;
import api.employee.model.MemberResponse;
import api.employee.model.MemberUpdateForm;
import api.employee.service.MemberService;
import api.employee.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final OrganizationService organizationService;

    @PostMapping("/member")
    public void saveMember(MemberForm memberForm) {
        organizationService.joinMember(memberForm);
    }

    @GetMapping("/member-list")
    public List<MemberResponse> findAllMember() {
        return memberService.findAllMember();
    }

    @GetMapping("/member")
    public MemberResponse findOneMember(Long memberId) {
        return memberService.findOneMember(memberId);
    }

    @PutMapping("/member")
    public void updateMember(MemberUpdateForm memberUpdateForm) {
        organizationService.updateMember(memberUpdateForm);
    }

    @DeleteMapping("/member")
    public void deleteMember(Long memberId) {
        memberService.deleteMember(memberId);
    }
}
