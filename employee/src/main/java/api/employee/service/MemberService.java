package api.employee.service;

import api.employee.domain.Member;
import api.employee.domain.Team;
import api.employee.model.MemberForm;
import api.employee.model.MemberResponse;
import api.employee.model.MemberUpdateForm;
import api.employee.repository.MemberRepository;
import api.employee.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void saveMember(Member member) {
        memberRepository.save(member);
    }

    public List<MemberResponse> findAllMember() {
        return memberRepository.findAll().stream()
                .map(MemberResponse::fromMember)
                .toList();
    }

    public MemberResponse findOneMember(Long memberId) {
        Member member =  memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 직원입니다."));
        return MemberResponse.fromMember(member);
    }

    public Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 직원입니다."));
    }

    public Member findMemberByName(String name) {
        return memberRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 직원입니다."));
    }

    @Transactional
    public void deleteMember(Long memberId) {
        Member member = findMemberById(memberId);
        memberRepository.delete(member);
    }
}
