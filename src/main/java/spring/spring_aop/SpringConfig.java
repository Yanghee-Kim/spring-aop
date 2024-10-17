package spring.spring_aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.spring_aop.repository.MemberRepository;
import spring.spring_aop.repository.MemoryMemberRepository;
import spring.spring_aop.service.MemberService;

// @Service, @Repository 는 자동 의존관계 설정
// @Configuration은 직접 스프링 빈을 등록
@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository(); // 구현체를 return
    }
}
