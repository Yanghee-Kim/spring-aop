package spring.spring_aop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.spring_aop.domain.Member;
import spring.spring_aop.repository.MemberRepository;
import spring.spring_aop.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

@Transactional // jpa를 통한 데이터 변경은 트랜젝션 안에서 실행되어야 함
public class MemberService {
    private final MemberRepository memberRepository;

    // 외부에서 repository를 넣어준다 -> DI
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     * @param member
     * @return id
     */
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    public void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     * @return
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
